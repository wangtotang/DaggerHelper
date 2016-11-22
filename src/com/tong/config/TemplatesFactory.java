package com.tong.config;

import com.intellij.icons.AllIcons;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;
import com.intellij.openapi.fileTypes.StdFileTypes;

/**
 * Created by Tong on 2016/11/21.
 */
public class TemplatesFactory implements FileTemplateGroupDescriptorFactory{
    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor descriptor = new FileTemplateGroupDescriptor("plugin.descriptor", AllIcons.Nodes.Plugin);
        descriptor.addTemplate(new FileTemplateDescriptor("TActivity.java", StdFileTypes.JAVA.getIcon()));
        descriptor.addTemplate(new FileTemplateDescriptor("TApplication.java", StdFileTypes.JAVA.getIcon()));
        descriptor.addTemplate(new FileTemplateDescriptor("TApplicationComponent.java", StdFileTypes.JAVA.getIcon()));
        descriptor.addTemplate(new FileTemplateDescriptor("TApplicationModule.java", StdFileTypes.JAVA.getIcon()));
        descriptor.addTemplate(new FileTemplateDescriptor("TConfig.java", StdFileTypes.JAVA.getIcon()));
        descriptor.addTemplate(new FileTemplateDescriptor("TFragment.java", StdFileTypes.JAVA.getIcon()));
        descriptor.addTemplate(new FileTemplateDescriptor("TPageComponent.java", StdFileTypes.JAVA.getIcon()));
        descriptor.addTemplate(new FileTemplateDescriptor("TPageModule.java", StdFileTypes.JAVA.getIcon()));
        descriptor.addTemplate(new FileTemplateDescriptor("TPerPage.java", StdFileTypes.JAVA.getIcon()));
        return descriptor;
    }
}
