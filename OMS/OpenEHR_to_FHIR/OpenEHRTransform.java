package com.openMap1.mapper.FHIRTransforms;

import java.util.List;
import java.util.Vector;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.hl7.fhir.dstu3.model.*;
import org.hl7.fhir.dstu3.model.BaseResource;

import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.AllergyIntolerance.AllergyIntoleranceReactionComponent;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.AllergyIntolerance;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Bundle;

public class OpenEHRTransform extends BaseTransformer {

// to check that the compiled class is the correct version
public String getGeneratedDateTime() {return("Mon Feb 26 18:29:29 GMT 2018");}

// to check that the compiled class is the correct version; change version when making hand edits
public String getVersion() {return("1");}


/**
* @param  sDoc the input document
* @ return the result of the transform
*/
public BaseResource transform(Document sDoc) throws Exception
{
   Element root = sDoc.getDocumentElement();
   return topRule(root);
}

//--------------------------------------------------------------------------------------
//                                  Bundle                                  
//--------------------------------------------------------------------------------------

/**
* @param sourceTop
*/
protected BaseResource topRule(Element sourceTop) throws Exception
{
    if (!("composition".equals(getName(sourceTop))))
        throw new Exception("Root element is not named 'composition'");
    if (valueTest(sourceTop,"archetype_details/archetype_id/value", "openEHR-EHR-COMPOSITION.adverse_reaction_list.v1","=") && valueTest(sourceTop,"@archetype_node_id", "openEHR-EHR-COMPOSITION.adverse_reaction_list.v1","=") && valueTest(sourceTop,"@xsi:type", "COMPOSITION","=") && valueTest(sourceTop,"name/value", "Adverse reaction list","="))
    {
        Bundle target = new Bundle();

        List<Element> stack1 = push(sourceTop, new Vector<Element>());
        pLanguage(stack1, target);
        rBundle_Entry_Allerg(stack1, target);
        rBundle_Entry_Practi(stack1, target);
        return target;
    }
    else return null;
}

/**
* @param stack - source elements (0)composition; 
* @param target - reached by target path: Bundle
*/
protected void pLanguage(List<Element> stack, Bundle target) throws Exception
{
    Element sourceTop = stack.get(0);
    for(Element sLanguage : namedChildElements(sourceTop,"language"))
    {
        List<Element> stack1 = push(sLanguage,stack);

        Node sLanguage_1 = namedChildNode(sLanguage,"code_string");
        if (sLanguage_1 != null) target.setLanguage(getText(sLanguage_1));
    }
}

//--------------------------------------------------------------------------------------
//                                  Practitioner                                  
//--------------------------------------------------------------------------------------

/**
* @param stack - source elements (0)composition; 
* @param target - reached by target path: Bundle
*/
protected void rBundle_Entry_Practi(List<Element> stack, Bundle target) throws Exception
{
    Element sourceTop = stack.get(0);
    for(Element sComposer : namedChildElements(sourceTop,"composer"))
    {
        List<Element> stack1 = push(sComposer,stack);
         for(Element sName : namedChildElements(sComposer,"name"))
         {
             Bundle.BundleEntryComponent t_entry_Practitioner = new Bundle.BundleEntryComponent(); 
             target.addEntry(t_entry_Practitioner);
             Practitioner t_resource_Practitio = new Practitioner();
             t_entry_Practitioner.setResource(t_resource_Practitio);
             HumanName t_name_HumanName = new HumanName(); 
             t_resource_Practitio.addName(t_name_HumanName);
             t_name_HumanName.setFamily(getText(sName));
         }
    }
}

//--------------------------------------------------------------------------------------
//                                  AllergyIntolerance                                  
//--------------------------------------------------------------------------------------

/**
* @param stack - source elements (0)composition; 
* @param target - reached by target path: Bundle
*/
protected void rBundle_Entry_Allerg(List<Element> stack, Bundle target) throws Exception
{
    Element sourceTop = stack.get(0);
    for(Element sContent : namedChildElements(sourceTop,"content"))
    if (valueTest(sContent,"archetype_details/archetype_id/value", "openEHR-EHR-SECTION.allergies_adverse_reactions_rcp.v1","=") && valueTest(sContent,"@archetype_node_id", "openEHR-EHR-SECTION.allergies_adverse_reactions_rcp.v1","=") && valueTest(sContent,"@xsi:type", "SECTION","=") && valueTest(sContent,"name/value", "Allergies and adverse reactions","="))
    {
        List<Element> stack1 = push(sContent,stack);
         for(Element sItems : namedChildElements(sContent,"items"))
         if (valueTest(sItems,"archetype_details/archetype_id/value", "openEHR-EHR-EVALUATION.adverse_reaction_risk.v1","=") && valueTest(sItems,"@archetype_node_id", "openEHR-EHR-EVALUATION.adverse_reaction_risk.v1","=") && valueTest(sItems,"@xsi:type", "EVALUATION","=") && valueTest(sItems,"name/value", "Adverse reaction risk","="))
         {
             Bundle.BundleEntryComponent t_entry_AllergyIntol = new Bundle.BundleEntryComponent(); 
             target.addEntry(t_entry_AllergyIntol);
             AllergyIntolerance t_resource_AllergyIn = new AllergyIntolerance();
             t_entry_AllergyIntol.setResource(t_resource_AllergyIn);
             List<Element> stack2 = push(sItems,stack1);
             rPatient_Reference_2(stack2, t_resource_AllergyIn);
             rAllergyIntolerance_(stack2, t_resource_AllergyIn);
         }
    }
}

/**
* @param stack - source elements (0)composition; (1)content_at0000_Allergies_and_adverse_reactions; (2)items_at0000_Adverse_reaction_risk; 
* @param t_resource_AllergyIn - reached by target path: Bundle.entry.resource
*/
protected void rPatient_Reference_2(List<Element> stack, AllergyIntolerance t_resource_AllergyIn) throws Exception
{
    Element sItems = stack.get(2);
    for(Element sSubject : namedChildElements(sItems,"subject"))
    {
        Reference t_patient_Reference_ = new Reference(); 
        t_resource_AllergyIn.setPatient(t_patient_Reference_);
        List<Element> stack1 = push(sSubject,stack);

        Node sDisplay = namedChildNode(sSubject,"name");
        if (sDisplay != null) t_patient_Reference_.setDisplay(getText(sDisplay));
        pReference(stack1, t_patient_Reference_);
    }
}

/**
* @param stack - source elements (0)composition; (1)content_at0000_Allergies_and_adverse_reactions; (2)items_at0000_Adverse_reaction_risk; (3)subject; 
* @param t_patient_Reference_ - reached by target path: Bundle.entry.resource.patient
*/
protected void pReference(List<Element> stack, Reference t_patient_Reference_) throws Exception
{
    Element sSubject = stack.get(3);
    for(Element sIdentifiers : namedChildElements(sSubject,"identifiers"))
    {
        List<Element> stack1 = push(sIdentifiers,stack);

        Node sReference = namedChildNode(sIdentifiers,"id");
        if (sReference != null) t_patient_Reference_.setReference(getText(sReference));
    }
}

/**
* @param stack - source elements (0)composition; (1)content_at0000_Allergies_and_adverse_reactions; (2)items_at0000_Adverse_reaction_risk; 
* @param t_resource_AllergyIn - reached by target path: Bundle.entry.resource
*/
protected void rAllergyIntolerance_(List<Element> stack, AllergyIntolerance t_resource_AllergyIn) throws Exception
{
    Element sItems = stack.get(2);
    for(Element sData : namedChildElements(sItems,"data"))
    if (valueTest(sData,"@archetype_node_id", "at0001","=") && valueTest(sData,"@xsi:type", "ITEM_TREE","=") && valueTest(sData,"name/value", "Tree","="))
    {
        AllergyIntolerance.AllergyIntoleranceReactionComponent t_reaction_AllergyIn = new AllergyIntolerance.AllergyIntoleranceReactionComponent(); 
        t_resource_AllergyIn.addReaction(t_reaction_AllergyIn);
        List<Element> stack1 = push(sData,stack);
        rManifestation_Codea(stack1, t_reaction_AllergyIn);
        rSubstance_CodeableC(stack1, t_reaction_AllergyIn);
    }
}

/**
* @param stack - source elements (0)composition; (1)content_at0000_Allergies_and_adverse_reactions; (2)items_at0000_Adverse_reaction_risk; (3)data_at0001_Tree; 
* @param t_reaction_AllergyIn - reached by target path: Bundle.entry.resource.reaction
*/
protected void rManifestation_Codea(List<Element> stack, AllergyIntolerance.AllergyIntoleranceReactionComponent t_reaction_AllergyIn) throws Exception
{
    Element sData = stack.get(3);
    for(Element sItems_1 : namedChildElements(sData,"items"))
    if (valueTest(sItems_1,"@archetype_node_id", "at0009","=") && valueTest(sItems_1,"@xsi:type", "CLUSTER","=") && valueTest(sItems_1,"name/value", "Reaction details","="))
    {
        List<Element> stack1 = push(sItems_1,stack);
         for(Element sItems_2 : namedChildElements(sItems_1,"items"))
         if (valueTest(sItems_2,"@archetype_node_id", "at0011","=") && valueTest(sItems_2,"@xsi:type", "ELEMENT","=") && valueTest(sItems_2,"name/value", "Reaction","="))
         {
             List<Element> stack2 = push(sItems_2,stack1);
              for(Element sValue : namedChildElements(sItems_2,"value"))
              {
                  List<Element> stack3 = push(sValue,stack2);
                   for(Element sValue_1 : namedChildElements(sValue,"value"))
                   {
                       CodeableConcept t_manifestation_Code = new CodeableConcept(); 
                       t_reaction_AllergyIn.addManifestation(t_manifestation_Code);
                       Coding t_Coding = new Coding(); 
                       t_manifestation_Code.addCoding(t_Coding);
                       t_Coding.setSystem("http://snomed.info/sct");
                       t_Coding.setDisplay(getText(sValue_1));
                   }
              }
         }
    }
}

/**
* @param stack - source elements (0)composition; (1)content_at0000_Allergies_and_adverse_reactions; (2)items_at0000_Adverse_reaction_risk; (3)data_at0001_Tree; 
* @param t_reaction_AllergyIn - reached by target path: Bundle.entry.resource.reaction
*/
protected void rSubstance_CodeableC(List<Element> stack, AllergyIntolerance.AllergyIntoleranceReactionComponent t_reaction_AllergyIn) throws Exception
{
    Element sData = stack.get(3);
    for(Element sItems_3 : namedChildElements(sData,"items"))
    if (valueTest(sItems_3,"@archetype_node_id", "at0002","=") && valueTest(sItems_3,"@xsi:type", "ELEMENT","=") && valueTest(sItems_3,"name/value", "Causative agent","="))
    {
        List<Element> stack1 = push(sItems_3,stack);
         for(Element sValue_2 : namedChildElements(sItems_3,"value"))
         {
             CodeableConcept t_substance_Codeable = new CodeableConcept(); 
             t_reaction_AllergyIn.setSubstance(t_substance_Codeable);
             Coding t_Coding_1 = new Coding(); 
             t_substance_Codeable.addCoding(t_Coding_1);
             t_Coding_1.setSystem("http://snomed.info/sct");
             List<Element> stack2 = push(sValue_2,stack1);

             Node sDisplay_1 = namedChildNode(sValue_2,"value");
             if (sDisplay_1 != null) t_Coding_1.setDisplay(getText(sDisplay_1));
             pCode(stack2, t_Coding_1);
         }
    }
}

/**
* @param stack - source elements (0)composition; (1)content_at0000_Allergies_and_adverse_reactions; (2)items_at0000_Adverse_reaction_risk; (3)data_at0001_Tree; (4)items_at0002_Substance; (5)value; 
* @param t_Coding_1 - reached by target path: Bundle.entry.resource.reaction.substance.coding
*/
protected void pCode(List<Element> stack, Coding t_Coding_1) throws Exception
{
    Element sValue_2 = stack.get(5);
    for(Element sDefining : namedChildElements(sValue_2,"defining_code"))
    {
        List<Element> stack1 = push(sDefining,stack);

        Node sCode = namedChildNode(sDefining,"code_string");
        if (sCode != null) t_Coding_1.setCode(getText(sCode));
    }
}
}
