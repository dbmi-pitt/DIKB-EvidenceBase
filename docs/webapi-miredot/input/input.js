var com = { qmino : { miredot : {}}};
com.qmino.miredot.restApiSource = {"validLicence":true,"buildSystem":"maven 3","allowUsageTracking":true,"issuesTabHidden":false,"licenceErrorMessage":null,"miredotRevision":"25e24200396c","jsonDocHidden":false,"licenceHash":"1057301881564743121","miredotVersion":"1.5.1","baseUrl":"http:\/\/www.example.com","jsonDocEnabled":true,"dateOfGeneration":"2017-08-02 16:34:45","licenceType":"PRO","projectName":"WebAPI","projectVersion":"1.0.0-SNAPSHOT","projectTitle":"WebAPI-1.0.0-SNAPSHOT"};
com.qmino.miredot.restApiSource.tos = {
	org_ohdsi_webapi_mpevidence_Method_in: { "type": "complex", "name": "org_ohdsi_webapi_mpevidence_Method_in", "content": [] },
	org_ohdsi_webapi_mpevidence_Method_out: { "type": "complex", "name": "org_ohdsi_webapi_mpevidence_Method_out", "content": [] },
	org_ohdsi_webapi_mpevidence_Claim_in: { "type": "complex", "name": "org_ohdsi_webapi_mpevidence_Claim_in", "content": [] },
	org_ohdsi_webapi_mpevidence_Claim_out: { "type": "complex", "name": "org_ohdsi_webapi_mpevidence_Claim_out", "content": [] },
	org_ohdsi_webapi_mpevidence_DrugEntity_in: { "type": "complex", "name": "org_ohdsi_webapi_mpevidence_DrugEntity_in", "content": [] },
	org_ohdsi_webapi_mpevidence_DrugEntity_out: { "type": "complex", "name": "org_ohdsi_webapi_mpevidence_DrugEntity_out", "content": [] },
	org_ohdsi_webapi_mpevidence_Evidence_in: { "type": "complex", "name": "org_ohdsi_webapi_mpevidence_Evidence_in", "content": [] },
	org_ohdsi_webapi_mpevidence_Evidence_out: { "type": "complex", "name": "org_ohdsi_webapi_mpevidence_Evidence_out", "content": [] }
};

com.qmino.miredot.restApiSource.enums = {

};
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Method_in"].content = [ 
	{
		"name": "value",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "inferredMethod",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "method",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "evidence_index",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "mp_claim_id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false}
];
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Method_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Method_in"].comment = null;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Method_out"].content = [ 
	{
		"name": "value",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "inferredMethod",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "method",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "evidence_index",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "mp_claim_id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false}
];
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Method_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Method_out"].comment = null;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Claim_in"].content = [ 
	{
		"name": "label",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "mp_claim_id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "relationship",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "o_role_concept_code",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "o_concept_code",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "object",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "s_role_concept_code",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "s_concept_code",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "subject",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "method",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "claim_text",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "evidence",
		"comment": null,
		"typeValue": { "type": "map", "typeValue": com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Evidence_in"] },
		"deprecated": false}
];
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Claim_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Claim_in"].comment = null;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Claim_out"].content = [ 
	{
		"name": "label",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "mp_claim_id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "relationship",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "o_role_concept_code",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "o_concept_code",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "object",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "s_role_concept_code",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "s_concept_code",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "subject",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "method",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "claim_text",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "evidence",
		"comment": null,
		"typeValue": { "type": "map", "typeValue": com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Evidence_out"] },
		"deprecated": false}
];
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Claim_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Claim_out"].comment = null;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_DrugEntity_in"].content = [ 
	{
		"name": "CONCEPT_CLASS_ID",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "VOCABULARY_ID",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "CONCEPT_CODE",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "CONCEPT_NAME",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false}
];
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_DrugEntity_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_DrugEntity_in"].comment = null;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_DrugEntity_out"].content = [ 
	{
		"name": "CONCEPT_CLASS_ID",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "VOCABULARY_ID",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "CONCEPT_CODE",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false
	},
	{
		"name": "CONCEPT_NAME",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "string" },
		"deprecated": false}
];
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_DrugEntity_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_DrugEntity_out"].comment = null;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Evidence_in"].content = [ 
	{
		"name": "evRelationship",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "boolean" },
		"deprecated": false
	},
	{
		"name": "evidence_index",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "mp_claim_id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false}
];
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Evidence_in"].ordered = false;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Evidence_in"].comment = null;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Evidence_out"].content = [ 
	{
		"name": "evRelationship",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "boolean" },
		"deprecated": false
	},
	{
		"name": "evidence_index",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false
	},
	{
		"name": "mp_claim_id",
		"comment": null,
		"typeValue": { "type": "simple", "typeValue": "number" },
		"deprecated": false}
];
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Evidence_out"].ordered = false;
com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Evidence_out"].comment = null;
com.qmino.miredot.restApiSource.interfaces = [
	{
		"beschrijving": "",
		"url": "/mpevidence/{sourceKey}/method/{drugURI1}/{drugURI2}/{drug1Role}",
		"http": "GET",
		"title": "Query Micropublication Claim by specifying involved drugs",
		"tags": [],
		"authors": ["Yifan Ning"],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Method_out"] }, "comment": "list of Claim tagged by Method"},
		"statusCodes": [],
		"hash": "-2126846678",
		"inputs": {
                "PATH": [
                    {"name": "sourceKey", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "path parameter specifying the source key identifying the source to use for access to the set of vocabulary tables", "jaxrs": "PATH"},
                    {"name": "drugURI1", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "drug 1 URI in format \"[vocabulary id]-[concpet code]\"", "jaxrs": "PATH"},
                    {"name": "drugURI2", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "drug 2 URI in format \"[vocabulary id]-[concpet code]\"", "jaxrs": "PATH"},
                    {"name": "drug1Role", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "drug role as precipitant or object for 1st drug in Micropublication Claim", "jaxrs": "PATH"}
                ],
                "QUERY": [],
                "BODY": [],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/mpevidence/{sourceKey}/claim",
		"http": "GET",
		"title": "Query all Micropublication Claim",
		"tags": [],
		"authors": ["Yifan Ning"],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_Claim_out"] }, "comment": "list of Claim with Method information"},
		"statusCodes": [],
		"hash": "-462985883",
		"inputs": {
                "PATH": [{"name": "sourceKey", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "path parameter specifying the source key identifying the source to use for access to the set of vocabulary tables", "jaxrs": "PATH"}],
                "QUERY": [],
                "BODY": [],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/mpevidence/{sourceKey}/drugname2/{drug1URI}/{drug1Role}",
		"http": "GET",
		"title": "Query all second drug concept names that interact with specified first drug",
		"tags": [],
		"authors": ["Yifan Ning"],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_DrugEntity_out"] }, "comment": "list of DrugEntity as options for 2nd drug"},
		"statusCodes": [],
		"hash": "-985996237",
		"inputs": {
                "PATH": [
                    {"name": "sourceKey", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "path parameter specifying the source key identifying the source to use for access to the set of vocabulary tables", "jaxrs": "PATH"},
                    {"name": "drug1URI", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "drug 1 URI in format \"[vocabulary id]-[concpet code]\"", "jaxrs": "PATH"},
                    {"name": "drug1Role", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "drug role as precipitant or object for 1st drug in Micropublication Claim", "jaxrs": "PATH"}
                ],
                "QUERY": [],
                "BODY": [],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/mpevidence/{sourceKey}/search/{drugname1}/{drugname2}/{method}/{drug1Role}",
		"http": "GET",
		"title": "Query Micropublication Claim detailed information and Evidences associated with",
		"tags": [],
		"authors": ["Yifan Ning"],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"output": {"typeValue": { "type": "collection", "typeValue":{ "type": "simple", "typeValue": "object" } }, "comment": "list of Claim with evidences information"},
		"statusCodes": [],
		"hash": "-1009456311",
		"inputs": {
                "PATH": [
                    {"name": "sourceKey", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "path parameter specifying the source key identifying the source to use for access to the set of vocabulary tables", "jaxrs": "PATH"},
                    {"name": "drugname1", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "1st drug standardized concept name", "jaxrs": "PATH"},
                    {"name": "drugname2", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "2nd drug standardized concept name", "jaxrs": "PATH"},
                    {"name": "method", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "Micropublication method", "jaxrs": "PATH"},
                    {"name": "drug1Role", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "drug role as precipitant or object for 1st drug in Micropublication Claim", "jaxrs": "PATH"}
                ],
                "QUERY": [],
                "BODY": [],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	},
	{
		"beschrijving": "",
		"url": "/mpevidence/{sourceKey}/drugname/{drugRole}",
		"http": "GET",
		"title": "Query all drug concept names that appears in Micropublication Claim in evidence base",
		"tags": [],
		"authors": ["Yifan Ning"],
		"compressed": false,
		"deprecated": false,
		"consumes": [],
		"produces": ["application/json"],
		"roles": [],
		"output": {"typeValue": { "type": "collection", "typeValue":com.qmino.miredot.restApiSource.tos["org_ohdsi_webapi_mpevidence_DrugEntity_out"] }, "comment": "list of DrugEntity"},
		"statusCodes": [],
		"hash": "1514948561",
		"inputs": {
                "PATH": [
                    {"name": "sourceKey", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "path parameter specifying the source key identifying the source to use for access to the set of vocabulary tables", "jaxrs": "PATH"},
                    {"name": "drugRole", "typeValue": { "type": "simple", "typeValue": "string" }, "comment": "drug role as precipitant or object for 1st drug in Micropublication Claim", "jaxrs": "PATH"}
                ],
                "QUERY": [],
                "BODY": [],
                "HEADER": [],
                "COOKIE": [],
                "FORM": [],
                "MATRIX": []
            }
	}];
com.qmino.miredot.projectWarnings = [
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": "-2126846678",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getEvidenceType",
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": "-2126846678",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getEvidenceType",
		"entity": "java.lang.Exception"
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": "-2126846678",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getEvidenceType",
		"entity": "java.lang.Exception"
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": "-462985883",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getAllClaim",
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": "-462985883",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getAllClaim",
		"entity": "java.lang.Exception"
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": "-462985883",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getAllClaim",
		"entity": "java.lang.Exception"
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": "-985996237",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getSecondDrugName",
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": "-985996237",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getSecondDrugName",
		"entity": "java.lang.Exception"
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": "-985996237",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getSecondDrugName",
		"entity": "java.lang.Exception"
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": "-1009456311",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "searchEvidence",
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": "-1009456311",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "searchEvidence",
		"entity": "java.lang.Exception"
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": "-1009456311",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "searchEvidence",
		"entity": "java.lang.Exception"
	},
	{
		"category": "JAVADOC_MISSING_INTERFACEDOCUMENTATION",
		"description": "Missing interface documentation",
		"failedBuild": false,
		"interface": "1514948561",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getAllDrugName",
		"entity": null
	},
	{
		"category": "JAVADOC_MISSING_EXCEPTION_DOCUMENTATION",
		"description": "Exception thrown by method has no comment",
		"failedBuild": false,
		"interface": "1514948561",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getAllDrugName",
		"entity": "java.lang.Exception"
	},
	{
		"category": "REST_UNMAPPED_EXCEPTION",
		"description": "Exception is thrown by interface specification, but is not mapped in the MireDot configuration. As such, the return errorcode can not be documented properly.",
		"failedBuild": false,
		"interface": "1514948561",
		"implementationClass": "org.ohdsi.webapi.service.MPEvidenceService",
		"implementationMethod": "getAllDrugName",
		"entity": "java.lang.Exception"
	}];
com.qmino.miredot.processErrors  = [
];

