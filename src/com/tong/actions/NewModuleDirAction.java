package com.tong.actions;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.tong.ui.BaseDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

/**
 * Created by Tong on 2016/11/20.
 */
public class NewModuleDirAction extends BaseAction {

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
    }

    @NotNull
    @Override
    protected void invokeDialog(AnActionEvent e,Project project, PsiDirectory dir) {
        BaseDialog dialog = new BaseDialog("请输入基类前缀:",project);
        dialog.show();
        if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            String prefix = dialog.getClassNamePrefix();
            if(prefix != null && !prefix.isEmpty()){
                Application application = ApplicationManager.getApplication();
                application.runWriteAction(() -> {
                    PsiElement createdElement = createProjectDir(dir,prefix);
                    final IdeView view = e.getData(LangDataKeys.IDE_VIEW);
                    if(view != null&&createdElement != null){
                        view.selectElement(createdElement);
                    }
                });
            }
        }
    }


    private PsiElement createProjectDir(PsiDirectory dir, String prefix){
        PsiDirectory module = dir.createSubdirectory(prefix.toLowerCase());
        PsiElement createdElement = createProjectCls(module,prefix,"TComponent.java");
        createProjectCls(module,prefix+"Module","TModule.java");

        module.createSubdirectory("activity");
        module.createSubdirectory("provider");
        module.createSubdirectory("presenter");

        return  createdElement;

    }

}
