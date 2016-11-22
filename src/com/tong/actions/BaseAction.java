package com.tong.actions;

import com.intellij.ide.IdeView;
import com.intellij.ide.actions.CreateElementActionBase;
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
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.tong.ui.BaseDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

/**
 * Created by Tong on 2016/11/20.
 */
public abstract class BaseAction extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
        final Presentation presentation = e.getPresentation();
        if(presentation.isEnabled()) {
            final IdeView view = e.getData(LangDataKeys.IDE_VIEW);
            final Project project = e.getProject();
            if (view != null && project != null) {
                ProjectFileIndex projectFileIndex = ProjectRootManager.getInstance(project).getFileIndex();
                PsiDirectory[] dirs = view.getDirectories();
                for (PsiDirectory dir : dirs) {
                    if (projectFileIndex.isUnderSourceRootOfType(dir.getVirtualFile(), JavaModuleSourceRootTypes.SOURCES) &&
                            JavaDirectoryService.getInstance().getPackage(dir) != null && isValidForClass(project,dir)) {
                        return;
                    }
                }
            }
            presentation.setEnabled(false);
            presentation.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent e) {

        IdeView view = e.getData(LangDataKeys.IDE_VIEW);

        if(view == null) return;

        Project project = e.getProject();
        final PsiDirectory dir = view.getOrChooseDirectory();

        if(dir == null) return;

        invokeDialog(e,project,dir);

    }

    @NotNull
    protected abstract void invokeDialog(AnActionEvent e,Project project, PsiDirectory dir);

    protected boolean isValidForClass(final Project project,final PsiDirectory directory) {
        Module module = ModuleUtil.findModuleForPsiElement(directory);
        if (module == null) {
            return false;
        }
        GlobalSearchScope moduleScope = module.getModuleWithDependenciesAndLibrariesScope(false);
        PsiClass classInModule = JavaPsiFacade.getInstance(project).findClass("dagger.Component", moduleScope);
        return classInModule != null&& findAndroidSDK() != null;
    }

    protected Sdk findAndroidSDK() {
        Sdk[] allJDKs = ProjectJdkTable.getInstance().getAllJdks();
        for (Sdk sdk : allJDKs) {
            if (sdk.getSdkType().getName().toLowerCase().contains("android")) {
                return sdk;
            }
        }
        return null; // no Android SDK found
    }

    protected PsiClass createProjectCls(PsiDirectory directory,String newName,String classTemplete){
        return JavaDirectoryService.getInstance().createClass(directory, newName, classTemplete);
    }

}
