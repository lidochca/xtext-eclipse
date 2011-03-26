/*
 * generated by Xtext
 */
package org.eclipse.xtext.ui.codetemplates.ui;

import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.service.SingletonBinding;
import org.eclipse.xtext.ui.LexerUIBindings;
import org.eclipse.xtext.ui.codetemplates.ui.contentassist.CodetemplatesProposalConflictHelper;
import org.eclipse.xtext.ui.codetemplates.ui.highlighting.SemanticHighlighter;
import org.eclipse.xtext.ui.codetemplates.ui.highlighting.TemplatesHighlightingConfiguration;
import org.eclipse.xtext.ui.codetemplates.ui.highlighting.TemplatesTokenDefProvider;
import org.eclipse.xtext.ui.codetemplates.ui.highlighting.TokenToAttributeMapper;
import org.eclipse.xtext.ui.codetemplates.ui.preferences.TemplatesLanguageConfiguration;
import org.eclipse.xtext.ui.codetemplates.ui.scoping.SyntheticResourceAwareScopeProvider;
import org.eclipse.xtext.ui.codetemplates.ui.validation.TemplateValidator;
import org.eclipse.xtext.ui.codetemplates.validation.CodetemplatesJavaValidator;
import org.eclipse.xtext.ui.editor.contentassist.IProposalConflictHelper;
import org.eclipse.xtext.ui.editor.contentassist.RepeatedContentAssistProcessor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import com.google.inject.Binder;
import com.google.inject.Provider;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used within the IDE.
 */
public class CodetemplatesUiModule extends org.eclipse.xtext.ui.codetemplates.ui.AbstractCodetemplatesUiModule {
	public CodetemplatesUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}
	
	@Override
	public void configureHighlightingTokenDefProvider(Binder binder) {
		binder.bind(ITokenDefProvider.class).annotatedWith(Names.named(LexerUIBindings.HIGHLIGHTING)).to(TemplatesTokenDefProvider.class);
	}
	
	public Class<? extends AbstractAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
		return TokenToAttributeMapper.class;
	}
	
	public Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
		return TemplatesHighlightingConfiguration.class;
	}
	
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return SyntheticResourceAwareScopeProvider.class;
	}
	
	public Class<? extends ISemanticHighlightingCalculator> bindSemanticHighlightingCalculator() {
		return SemanticHighlighter.class;
	}
	
	@SingletonBinding(eager=true)	
	public Class<? extends CodetemplatesJavaValidator> bindTemplatesJavaValidator() {
		return TemplateValidator.class;
	}
	
	@Override
	public Class<? extends IContentAssistProcessor> bindIContentAssistProcessor() {
		return RepeatedContentAssistProcessor.class;
	}
	
	@Override
	public Class<? extends IProposalConflictHelper> bindIProposalConflictHelper() {
		return CodetemplatesProposalConflictHelper.class;
	}
	
	@Override
	public Provider<TemplatesLanguageConfiguration> provideTemplatesLanguageConfiguration() {
		return new Provider<TemplatesLanguageConfiguration>() {
			public TemplatesLanguageConfiguration get() {
				return AccessibleCodetemplatesActivator.getTemplatesLanguageConfigurationProvider().get();
			}
		};
	}
}
