/*
* generated by Xtext
*/
package org.eclipse.xtext.xbase.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.common.types.JvmAnnotationType;
import org.eclipse.xtext.common.types.JvmAnyTypeReference;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XCasePart;
import org.eclipse.xtext.xbase.XSwitchExpression;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.typesystem.IBatchTypeResolver;
import org.eclipse.xtext.xbase.typesystem.IResolvedTypes;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typing.ITypeProvider;
import org.eclipse.xtext.xbase.validation.UIStrings;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XImportSection;

import com.google.inject.Inject;

/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class XbaseLabelProvider extends DefaultEObjectLabelProvider {
	
	@Inject
	private XbaseImages images;
	
	@Inject
	private UIStrings uiStrings;
	
	@Inject
	private IBatchTypeResolver typeResolver;
	
	@Inject
	public XbaseLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}
	
	public Image image(XImportSection importSection) {
		return images.forImportContainer();
	}
	
	public Image image(XImportDeclaration importDeclaration) {
		return images.forImport();
	}
	
	public Image image(JvmGenericType genericType){
		if(genericType.isInterface())
			return images.forInterface(genericType.getVisibility());
		else 
			return images.forClass(genericType.getVisibility());
	}
	
	public Image image(JvmEnumerationType genericType){
		return images.forEnum(genericType.getVisibility());
	}
	
	public Image image(JvmAnnotationType genericType){
		return images.forAnnotation(genericType.getVisibility());
	}
	
	public String test(JvmGenericType genericType){
		return genericType.getSimpleName();
	}
	
	public Image image(JvmOperation element) {
		return images.forOperation(element.getVisibility(), element.isAbstract(), element.isStatic(), element.isFinal());
	}
	
	public Object text(JvmOperation element) {
		return signature(element.getSimpleName(), element);
	}

	public Image image(JvmConstructor element) {
		return images.forConstructor(element.getVisibility());
	}
	
	public String text(JvmConstructor element) {
		return "new" + uiStrings.parameters(element);
	}
	
	public Image image(JvmField element) {
		return images.forField(element.getVisibility(), element.isStatic(), element.isFinal(), false);
	}
	
	public String text(JvmField element) {
		return element.getSimpleName() +" : " + element.getType().getSimpleName();
	}
	
	public Image image(JvmFormalParameter parameter){
		return images.forLacalVariable();
	}
	
	public String text(JvmFormalParameter parameter){
		JvmTypeReference parameterType = parameter.getParameterType();
		String result = parameter.getName();
		if(parameterType != null)
			result =  parameterType.getSimpleName() + " " + result;
		return result;
	}
	
	public Image image(XVariableDeclaration variableDeclaration){
		return images.forLacalVariable();
	}
	
	public Image image(JvmTypeParameter parameter){
		return images.forTypeParameter();
	}

	public String text(XVariableDeclaration variableDeclaration){
		IResolvedTypes resolvedTypes = typeResolver.resolveTypes(variableDeclaration);
		LightweightTypeReference type = resolvedTypes.getActualType((JvmIdentifiableElement)variableDeclaration);
		String result = variableDeclaration.getName();
		if(type != null)
			result =  type.getSimpleName() + " " + result;
		return result;
	}
	
	public String text(XCasePart casePart){
		if (casePart.eContainer() instanceof XSwitchExpression) {
			XSwitchExpression switchExpression = (XSwitchExpression) casePart.eContainer();
			if (switchExpression != null) {
				if (switchExpression.getLocalVarName() != null)
					return switchExpression.getLocalVarName();
				if (switchExpression.getSwitch() instanceof XAbstractFeatureCall) {
					XAbstractFeatureCall call = (XAbstractFeatureCall) switchExpression.getSwitch();
					if (call.getFeature() != null)
						return call.getFeature().getSimpleName();
				}
			}
		}
		return null;
	}
	
	protected StyledString signature(String simpleName, JvmIdentifiableElement element) {
		IResolvedTypes resolvedTypes = typeResolver.resolveTypes(element);
		LightweightTypeReference returnType = resolvedTypes.getActualType(element);
		String returnTypeString = "void";
		if (returnType != null) {
			returnTypeString = returnType.getSimpleName();
		}
		return new StyledString(simpleName + uiStrings.parameters(element)).append(new StyledString(" : " + returnTypeString,StyledString.DECORATIONS_STYLER));
	}
}
