Transforms to FHIR

This folder contains two transforms:

--   From an HL7 V2 ADT to a FHIR bundle
--   From an OpenEHR Adverse Reaction composition to a FHIR bundle

For each transform, test cases and Java source code are provided. The transforms are not yet complete.
You are free to adapt and use these transforms. Bi-directional transforms are available on request.

The Java source code was auto-generated using the Transforms By Example (TBE) toolset.
These tools require one or more test cases (source-target example pairs) to create Java transform code.

The test cases in each folder were used to generate the Java transform code in that folder.
You can adapt and extend the transforms by extending the test cases and using the TBE tools.
The ADT transform uses the XML form of an ADT message, but transforms can also be made from pipe-hat form.
For both transforms, the Java code uses the HAPI FHIR Java libraries, and can output FHIR as XML or JSON.

For more information about the TBE tools, see http://www.openmapsw.com/TBE/TBEGeneral.htm .
