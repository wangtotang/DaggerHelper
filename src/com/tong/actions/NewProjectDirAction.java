package com.tong.actions;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.tong.ui.BaseDialog;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Tong on 2016/11/20.
 */
public class NewProjectDirAction extends BaseAction {

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
                    PsiElement[] elements = createProjectDir(dir,prefix);
                    final IdeView view = e.getData(LangDataKeys.IDE_VIEW);
                    if(view != null){
                        for (PsiElement element : elements) {
                            view.selectElement(element);
                        }
                    }
                });
            }
        }
    }


    private PsiElement[] createProjectDir(PsiDirectory dir, String prefix){
        PsiDirectory common = dir.createSubdirectory("common");
        createProjectCls(common,"ApplicationComponent","TApplicationComponent.java");
        createProjectCls(common,"ApplicationModule","TApplicationModule.java");
        createProjectCls(common,prefix+"Application","TApplication.java");
        createProjectCls(common,"Config","TConfig.java");

        PsiDirectory bases = common.createSubdirectory("bases");
        PsiElement pageComponent = createProjectCls(bases,"PageComponent","TPageComponent.java");
        createProjectCls(bases,"PageModule","TPageModule.java");
        createProjectCls(bases,"PerPage","TPerPage.java");
        PsiElement activity = createProjectCls(bases,prefix,"TActivity.java");
        PsiElement fragment = createProjectCls(bases,prefix,"TFragment.java");

        dir.createSubdirectory("jni");
        dir.createSubdirectory("module");
        dir.createSubdirectory("net");
        dir.createSubdirectory("storage");

        return new PsiElement[]{pageComponent,activity,fragment};
    }

}
