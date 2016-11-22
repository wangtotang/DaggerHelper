package com.tong.actions;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.search.GlobalSearchScope;
import com.tong.ui.BaseDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

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
                    createProjectDir(dir,prefix);
                });
            }
        }
    }


    private void createProjectDir(PsiDirectory dir, String prefix){
        PsiDirectory common = dir.createSubdirectory("common");

        PsiDirectory bases = common.createSubdirectory("bases");
        createProjectCls(bases,"ApplicationComponent","TApplicationComponent.java");
        createProjectCls(bases,"ApplicationModule","TApplicationModule.java");
        createProjectCls(bases,prefix+"Application","TApplication.java");
        createProjectCls(bases,"PageComponent","TPageComponent.java");
        createProjectCls(bases,"PageModule","TPageModule.java");
        createProjectCls(bases,"PerPage","TPerPage.java");
        createProjectCls(bases,prefix,"TActivity.java");
        createProjectCls(bases,prefix,"TFragment.java");

        PsiDirectory config = common.createSubdirectory("config");
        createProjectCls(config,"Config","TConfig.java");

        dir.createSubdirectory("jni");
        dir.createSubdirectory("module");
        dir.createSubdirectory("net");
        dir.createSubdirectory("storage");
    }

}
