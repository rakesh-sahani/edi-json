# edi-json
834 - Benefit Enrollment and Maintenance


HIPAA Role in Implementation Guides

The Health Insurance Portability and Accountability Act of 1996 (P.L.104-191 known as HIPAA) includes provisions for Administrative Simplification, which require the Secretary of Department of Health and Human Services to adopt standards to support the electronic exchange of administrative and financial health care transactions primarily between health care providers and plans. HIPAA directs the Secretary to adopt standards for transactions to enable health information to be exchanged electronically and to adopt specifications for implementing each standard. Detailed Implementation Guides for each standard must be available at the time of the adoption of HIPAA standards so that health plans, providers, clearinghouses, and software vendors can ready their information systems and application software for compliance with the standards. Consistent usage of the standards, including loops, segments, data elements, etc., across all guides is mandatory to support the Secretary’s commitment to standardization. This Implementation Guide has been developed for use as a HIPAA Implementation Guide for Enrollment and Disenrollment in a Health Plan.  Should the Secretary adopt the X12N 834 Benefit Enrollment and Maintenance transaction as an industry standard, this Implementation Guide describes the consistent industry usage called for by HIPAA. If adopted under HIPAA, the X12N 834 Benefit Enrollment and Maintenance transaction cannot be implemented except as described in this Implementation Guide.

https://www.utsystem.edu/sites/default/files/offices/employee-benefits/docs/X095.pdf


```json
{
    "SegmentDelimiter": "~",
    "DataElementDelimiter": "*",
    "ISA": {
        "AuthorizationInformationQualifier_01": "00",
        "AuthorizationInformation_02": "          ",
        "SecurityInformationQualifier_03": "00",
        "SecurityInformation_04": "          ",
        "SenderIDQualifier_05": "30",
        "InterchangeSenderID_06": "311418743      ",
        "ReceiverIDQualifier_07": "30",
        "InterchangeReceiverID_08": "9999999999     ",
        "InterchangeDate_09": "230501",
        "InterchangeTime_10": "1046",
        "InterchangeControlStandardsIdentifier_11": "^",
        "InterchangeControlVersionNumber_12": "00501",
        "InterchangeControlNumber_13": "682952406",
        "AcknowledgementRequested_14": "0",
        "UsageIndicator_15": "P",
        "ComponentElementSeparator_16": ":"
    },
    "Groups": [
        {
            "GS": {
                "CodeIdentifyingInformationType_01": "BE",
                "SenderIDCode_02": "311418743",
                "ReceiverIDCode_03": null,
                "Date_04": "20230501",
                "Time_05": "1046",
                "GroupControlNumber_06": "682952406",
                "TransactionTypeCode_07": "X",
                "VersionAndRelease_08": "005010X220A1"
            },
            "Transactions": [
                {
                    "ST": {
                        "TransactionSetIdentifierCode_01": "834",
                        "TransactionSetControlNumber_02": "000000001",
                        "ImplementationConventionPreference_03": "005010X220A1"
                    },
                    "BGN_BeginningSegment": {
                        "TransactionSetPurposeCode_01": "00",
                        "TransactionSetReferenceNumber_02": "682952406",
                        "TransactionSetCreationDate_03": "20230501",
                        "TransactionSetCreationTime_04": "1046",
                        "TimeZoneCode_05": "ET",
                        "OriginalTransactionSetReferenceNumber_06": "",
                        "TransactionTypeCode_07": "",
                        "ActionCode_08": "4",
                        "SecurityLevelCode_09": null
                    },
                    "REF_TransactionSetPolicyNumber": null,
                    "DTP_FileEffectiveDate": [
                        {
                            "DateTimeQualifier_01": "007",
                            "DateTimePeriodFormatQualifier_02": "D8",
                            "DateTimePeriod_03": "20230501"
                        }
                    ],
                    "QTY_TransactionSetControlTotals": [],
                    "AllN1": {
                        "Loop1000A": {
                            "N1_SponsorName": {
                                "EntityIdentifierCode_01": "P5",
                                "PremiumPayerName_02": "S&S HEALTH",
                                "IdentificationCodeQualifier_03": "FI",
                                "IntermediaryBankIdentifier_04": "311418743",
                                "EntityRelationshipCode_05": null,
                                "EntityIdentifierCode_06": null
                            }
                        },
                        "Loop1000B": {
                            "N1_Payer": {
                                "EntityIdentifierCode_01": "IN",
                                "PremiumPayerName_02": "MEDXOOM",
                                "IdentificationCodeQualifier_03": "FI",
                                "IntermediaryBankIdentifier_04": "9999999999",
                                "EntityRelationshipCode_05": null,
                                "EntityIdentifierCode_06": null
                            }
                        },
                        "Loop1000C": []
                    },
                    "Loop2000": [
                        {
                            "INS_MemberLevelDetail": {
                                "InsuredIndicator_01": "Y",
                                "IndividualRelationshipCode_02": "18",
                                "MaintenanceTypeCode_03": "030",
                                "MaintenanceReasonCode_04": "XN",
                                "BenefitStatusCode_05": "A",
                                "MedicareStatusCode_06": {
                                    "MedicarePlanCode_01": "E",
                                    "EligibilityReasonCode_02": null,
                                    "EligibilityReasonCode_03": null,
                                    "EligibilityReasonCode_04": null
                                },
                                "ConsolidatedOmnibusBudgetReconciliationActCOBRAQualifyingEventCode_07": "",
                                "EmploymentStatusCode_08": "FT",
                                "StudentStatusCode_09": "",
                                "YesNoConditionOrResponseCode_10": "N",
                                "DateTimePeriodFormatQualifier_11": null,
                                "DateTimePeriod_12": null,
                                "ConfidentialityCode_13": null,
                                "CityName_14": null,
                                "StateOrProvinceCode_15": null,
                                "CountryCode_16": null,
                                "BirthSequenceNumber_17": null
                            },
                            "AllREF": {
                                "REF_SubscriberIdentifier": {
                                    "ReferenceIdentificationQualifier_01": "0F",
                                    "MemberGroupOrPolicyNumber_02": "001022763",
                                    "Description_03": null,
                                    "ReferenceIdentifier_04": {
                                        "ReferenceIdentificationQualifier_01": null,
                                        "ReferenceIdentification_02": null,
                                        "ReferenceIdentificationQualifier_03": null,
                                        "ReferenceIdentification_04": null,
                                        "ReferenceIdentificationQualifier_05": null,
                                        "ReferenceIdentification_06": null
                                    }
                                },
                                "REF_MemberPolicyNumber": {
                                    "ReferenceIdentificationQualifier_01": "1L",
                                    "MemberGroupOrPolicyNumber_02": "10091",
                                    "Description_03": null,
                                    "ReferenceIdentifier_04": {
                                        "ReferenceIdentificationQualifier_01": null,
                                        "ReferenceIdentification_02": null,
                                        "ReferenceIdentificationQualifier_03": null,
                                        "ReferenceIdentification_04": null,
                                        "ReferenceIdentificationQualifier_05": null,
                                        "ReferenceIdentification_06": null
                                    }
                                },
                                "REF_MemberSupplementalIdentifier": [
                                    {
                                        "ReferenceIdentificationQualifier_01": "23",
                                        "MemberGroupOrPolicyNumber_02": "ME0990214",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    },
                                    {
                                        "ReferenceIdentificationQualifier_01": "DX",
                                        "MemberGroupOrPolicyNumber_02": "1000",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    },
                                    {
                                        "ReferenceIdentificationQualifier_01": "ZZ",
                                        "MemberGroupOrPolicyNumber_02": "01",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    }
                                ]
                            },
                            "DTP_MemberLevelDates": [
                                {
                                    "DateTimeQualifier_01": "336",
                                    "DateTimePeriodFormatQualifier_02": "D8",
                                    "DateTimePeriod_03": "20191018"
                                },
                                {
                                    "DateTimeQualifier_01": "356",
                                    "DateTimePeriodFormatQualifier_02": "D8",
                                    "DateTimePeriod_03": "20220901"
                                }
                            ],
                            "AllNM1": {
                                "Loop2100A": {
                                    "NM1_MemberName": {
                                        "EntityIdentifierCode_01": "IL",
                                        "EntityTypeQualifier_02": "1",
                                        "ResponseContactLastOrOrganizationName_03": "BROWN",
                                        "ResponseContactFirstName_04": "TIMOTHY",
                                        "ResponseContactMiddleName_05": "P",
                                        "NamePrefix_06": "",
                                        "ResponseContactNameSuffix_07": "",
                                        "IdentificationCodeQualifier_08": "34",
                                        "ResponseContactIdentifier_09": "001022763",
                                        "EntityRelationshipCode_10": null,
                                        "EntityIdentifierCode_11": null,
                                        "NameLastOrOrganizationName_12": null
                                    },
                                    "PER_MemberCommunicationsNumbers": {
                                        "ContactFunctionCode_01": "IP",
                                        "ResponseContactName_02": "",
                                        "CommunicationNumberQualifier_03": "TE",
                                        "ResponseContactCommunicationNumber_04": "6072795055",
                                        "CommunicationNumberQualifier_05": "EM",
                                        "ResponseContactCommunicationNumber_06": "TIMOTH.BROWN@TURBOENG.NET",
                                        "CommunicationNumberQualifier_07": null,
                                        "ResponseContactCommunicationNumber_08": null,
                                        "ContactInquiryReference_09": null
                                    },
                                    "N3_MemberResidenceStreetAddress": {
                                        "ResponseContactAddressLine_01": "214 BUTTERMILK LN",
                                        "ResponseContactAddressLine_02": null
                                    },
                                    "N4_MemberCity_State_ZIPCode": {
                                        "AdditionalPatientInformationContactCityName_01": "ITHICA",
                                        "AdditionalPatientInformationContactStateCode_02": "NY",
                                        "AdditionalPatientInformationContactPostalZoneOrZIPCode_03": "14850",
                                        "CountryCode_04": null,
                                        "LocationQualifier_05": null,
                                        "LocationIdentifier_06": null,
                                        "CountrySubdivisionCode_07": null
                                    },
                                    "DMG_MemberDemographics": {
                                        "DateTimePeriodFormatQualifier_01": "D8",
                                        "DependentBirthDate_02": "19641130",
                                        "DependentGenderCode_03": "M",
                                        "MaritalStatusCode_04": "M",
                                        "CompositeRaceOrEthnicityInformation_05": [
                                            {
                                                "RaceOrEthnicityCode_01": null,
                                                "CodeListQualifierCode_02": null,
                                                "IndustryCode_03": null
                                            }
                                        ],
                                        "CitizenshipStatusCode_06": null,
                                        "CountryCode_07": null,
                                        "BasisOfVerificationCode_08": null,
                                        "Quantity_09": null,
                                        "CodeListQualifierCode_10": null,
                                        "IndustryCode_11": null
                                    },
                                    "EC_EmploymentClass": [],
                                    "ICM_MemberIncome": null,
                                    "AMT_MemberPolicyAmounts": [],
                                    "HLH_MemberHealthInformation": null,
                                    "LUI_MemberLanguage": []
                                },
                                "Loop2100B": null,
                                "Loop2100C": null,
                                "Loop2100D": [],
                                "Loop2100E": [],
                                "Loop2100F": null,
                                "Loop2100G": [],
                                "Loop2100H": null
                            },
                            "Loop2200": [],
                            "Loop2300": [
                                {
                                    "HD_HealthCoverage": {
                                        "MaintenanceTypeCode_01": "030",
                                        "MaintenanceReasonCode_02": "",
                                        "InsuranceLineCode_03": "PDG",
                                        "PlanCoverageDescription_04": "MVPG",
                                        "CoverageLevelCode_05": "FAM",
                                        "Count_06": null,
                                        "Count_07": null,
                                        "UnderwritingDecisionCode_08": null,
                                        "LateEnrollmentIndicator_09": null,
                                        "DrugHouseCode_10": null,
                                        "YesNoConditionOrResponseCode_11": null
                                    },
                                    "DTP_HealthCoverageDates": [
                                        {
                                            "DateTimeQualifier_01": "348",
                                            "DateTimePeriodFormatQualifier_02": "D8",
                                            "DateTimePeriod_03": "20220901"
                                        }
                                    ],
                                    "AMT_HealthCoveragePolicy": [],
                                    "AllREF": {
                                        "REF_HealthCoveragePolicyNumber": [],
                                        "REF_PriorCoverageMonths": null
                                    },
                                    "IDC_IdentificationCard": [],
                                    "Loop2310": [],
                                    "Loop2320": []
                                },
                                {
                                    "HD_HealthCoverage": {
                                        "MaintenanceTypeCode_01": "030",
                                        "MaintenanceReasonCode_02": "",
                                        "InsuranceLineCode_03": "HLT",
                                        "PlanCoverageDescription_04": "MVPG",
                                        "CoverageLevelCode_05": "FAM",
                                        "Count_06": null,
                                        "Count_07": null,
                                        "UnderwritingDecisionCode_08": null,
                                        "LateEnrollmentIndicator_09": null,
                                        "DrugHouseCode_10": null,
                                        "YesNoConditionOrResponseCode_11": null
                                    },
                                    "DTP_HealthCoverageDates": [
                                        {
                                            "DateTimeQualifier_01": "348",
                                            "DateTimePeriodFormatQualifier_02": "D8",
                                            "DateTimePeriod_03": "20230101"
                                        }
                                    ],
                                    "AMT_HealthCoveragePolicy": [],
                                    "AllREF": {
                                        "REF_HealthCoveragePolicyNumber": [],
                                        "REF_PriorCoverageMonths": null
                                    },
                                    "IDC_IdentificationCard": [],
                                    "Loop2310": [],
                                    "Loop2320": []
                                }
                            ],
                            "LoopLS": null
                        },
                        {
                            "INS_MemberLevelDetail": {
                                "InsuredIndicator_01": "N",
                                "IndividualRelationshipCode_02": "01",
                                "MaintenanceTypeCode_03": "030",
                                "MaintenanceReasonCode_04": "XN",
                                "BenefitStatusCode_05": "A",
                                "MedicareStatusCode_06": {
                                    "MedicarePlanCode_01": "E",
                                    "EligibilityReasonCode_02": null,
                                    "EligibilityReasonCode_03": null,
                                    "EligibilityReasonCode_04": null
                                },
                                "ConsolidatedOmnibusBudgetReconciliationActCOBRAQualifyingEventCode_07": "",
                                "EmploymentStatusCode_08": "",
                                "StudentStatusCode_09": "",
                                "YesNoConditionOrResponseCode_10": "N",
                                "DateTimePeriodFormatQualifier_11": null,
                                "DateTimePeriod_12": null,
                                "ConfidentialityCode_13": null,
                                "CityName_14": null,
                                "StateOrProvinceCode_15": null,
                                "CountryCode_16": null,
                                "BirthSequenceNumber_17": null
                            },
                            "AllREF": {
                                "REF_SubscriberIdentifier": {
                                    "ReferenceIdentificationQualifier_01": "0F",
                                    "MemberGroupOrPolicyNumber_02": "001022763",
                                    "Description_03": null,
                                    "ReferenceIdentifier_04": {
                                        "ReferenceIdentificationQualifier_01": null,
                                        "ReferenceIdentification_02": null,
                                        "ReferenceIdentificationQualifier_03": null,
                                        "ReferenceIdentification_04": null,
                                        "ReferenceIdentificationQualifier_05": null,
                                        "ReferenceIdentification_06": null
                                    }
                                },
                                "REF_MemberPolicyNumber": {
                                    "ReferenceIdentificationQualifier_01": "1L",
                                    "MemberGroupOrPolicyNumber_02": "10091",
                                    "Description_03": null,
                                    "ReferenceIdentifier_04": {
                                        "ReferenceIdentificationQualifier_01": null,
                                        "ReferenceIdentification_02": null,
                                        "ReferenceIdentificationQualifier_03": null,
                                        "ReferenceIdentification_04": null,
                                        "ReferenceIdentificationQualifier_05": null,
                                        "ReferenceIdentification_06": null
                                    }
                                },
                                "REF_MemberSupplementalIdentifier": [
                                    {
                                        "ReferenceIdentificationQualifier_01": "23",
                                        "MemberGroupOrPolicyNumber_02": "ME0990214",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    },
                                    {
                                        "ReferenceIdentificationQualifier_01": "DX",
                                        "MemberGroupOrPolicyNumber_02": "1000",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    },
                                    {
                                        "ReferenceIdentificationQualifier_01": "ZZ",
                                        "MemberGroupOrPolicyNumber_02": "02",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    }
                                ]
                            },
                            "DTP_MemberLevelDates": [
                                {
                                    "DateTimeQualifier_01": "356",
                                    "DateTimePeriodFormatQualifier_02": "D8",
                                    "DateTimePeriod_03": "20220901"
                                }
                            ],
                            "AllNM1": {
                                "Loop2100A": {
                                    "NM1_MemberName": {
                                        "EntityIdentifierCode_01": "IL",
                                        "EntityTypeQualifier_02": "1",
                                        "ResponseContactLastOrOrganizationName_03": "BROWN",
                                        "ResponseContactFirstName_04": "PAULA",
                                        "ResponseContactMiddleName_05": "C",
                                        "NamePrefix_06": "",
                                        "ResponseContactNameSuffix_07": "",
                                        "IdentificationCodeQualifier_08": "34",
                                        "ResponseContactIdentifier_09": "272756011",
                                        "EntityRelationshipCode_10": null,
                                        "EntityIdentifierCode_11": null,
                                        "NameLastOrOrganizationName_12": null
                                    },
                                    "PER_MemberCommunicationsNumbers": null,
                                    "N3_MemberResidenceStreetAddress": {
                                        "ResponseContactAddressLine_01": "214 BUTTERMILK LANE",
                                        "ResponseContactAddressLine_02": null
                                    },
                                    "N4_MemberCity_State_ZIPCode": {
                                        "AdditionalPatientInformationContactCityName_01": "ITHICA",
                                        "AdditionalPatientInformationContactStateCode_02": "NY",
                                        "AdditionalPatientInformationContactPostalZoneOrZIPCode_03": "14850",
                                        "CountryCode_04": null,
                                        "LocationQualifier_05": null,
                                        "LocationIdentifier_06": null,
                                        "CountrySubdivisionCode_07": null
                                    },
                                    "DMG_MemberDemographics": {
                                        "DateTimePeriodFormatQualifier_01": "D8",
                                        "DependentBirthDate_02": "19680519",
                                        "DependentGenderCode_03": "F",
                                        "MaritalStatusCode_04": null,
                                        "CompositeRaceOrEthnicityInformation_05": [
                                            {
                                                "RaceOrEthnicityCode_01": null,
                                                "CodeListQualifierCode_02": null,
                                                "IndustryCode_03": null
                                            }
                                        ],
                                        "CitizenshipStatusCode_06": null,
                                        "CountryCode_07": null,
                                        "BasisOfVerificationCode_08": null,
                                        "Quantity_09": null,
                                        "CodeListQualifierCode_10": null,
                                        "IndustryCode_11": null
                                    },
                                    "EC_EmploymentClass": [],
                                    "ICM_MemberIncome": null,
                                    "AMT_MemberPolicyAmounts": [],
                                    "HLH_MemberHealthInformation": null,
                                    "LUI_MemberLanguage": []
                                },
                                "Loop2100B": null,
                                "Loop2100C": null,
                                "Loop2100D": [],
                                "Loop2100E": [],
                                "Loop2100F": null,
                                "Loop2100G": [],
                                "Loop2100H": null
                            },
                            "Loop2200": [],
                            "Loop2300": [
                                {
                                    "HD_HealthCoverage": {
                                        "MaintenanceTypeCode_01": "030",
                                        "MaintenanceReasonCode_02": "",
                                        "InsuranceLineCode_03": "PDG",
                                        "PlanCoverageDescription_04": "MVPG",
                                        "CoverageLevelCode_05": null,
                                        "Count_06": null,
                                        "Count_07": null,
                                        "UnderwritingDecisionCode_08": null,
                                        "LateEnrollmentIndicator_09": null,
                                        "DrugHouseCode_10": null,
                                        "YesNoConditionOrResponseCode_11": null
                                    },
                                    "DTP_HealthCoverageDates": [
                                        {
                                            "DateTimeQualifier_01": "348",
                                            "DateTimePeriodFormatQualifier_02": "D8",
                                            "DateTimePeriod_03": "20220901"
                                        }
                                    ],
                                    "AMT_HealthCoveragePolicy": [],
                                    "AllREF": {
                                        "REF_HealthCoveragePolicyNumber": [],
                                        "REF_PriorCoverageMonths": null
                                    },
                                    "IDC_IdentificationCard": [],
                                    "Loop2310": [],
                                    "Loop2320": []
                                },
                                {
                                    "HD_HealthCoverage": {
                                        "MaintenanceTypeCode_01": "030",
                                        "MaintenanceReasonCode_02": "",
                                        "InsuranceLineCode_03": "HLT",
                                        "PlanCoverageDescription_04": "MVPG",
                                        "CoverageLevelCode_05": null,
                                        "Count_06": null,
                                        "Count_07": null,
                                        "UnderwritingDecisionCode_08": null,
                                        "LateEnrollmentIndicator_09": null,
                                        "DrugHouseCode_10": null,
                                        "YesNoConditionOrResponseCode_11": null
                                    },
                                    "DTP_HealthCoverageDates": [
                                        {
                                            "DateTimeQualifier_01": "348",
                                            "DateTimePeriodFormatQualifier_02": "D8",
                                            "DateTimePeriod_03": "20230101"
                                        }
                                    ],
                                    "AMT_HealthCoveragePolicy": [],
                                    "AllREF": {
                                        "REF_HealthCoveragePolicyNumber": [],
                                        "REF_PriorCoverageMonths": null
                                    },
                                    "IDC_IdentificationCard": [],
                                    "Loop2310": [],
                                    "Loop2320": []
                                }
                            ],
                            "LoopLS": null
                        },
                        {
                            "INS_MemberLevelDetail": {
                                "InsuredIndicator_01": "N",
                                "IndividualRelationshipCode_02": "19",
                                "MaintenanceTypeCode_03": "030",
                                "MaintenanceReasonCode_04": "XN",
                                "BenefitStatusCode_05": "A",
                                "MedicareStatusCode_06": {
                                    "MedicarePlanCode_01": "E",
                                    "EligibilityReasonCode_02": null,
                                    "EligibilityReasonCode_03": null,
                                    "EligibilityReasonCode_04": null
                                },
                                "ConsolidatedOmnibusBudgetReconciliationActCOBRAQualifyingEventCode_07": "",
                                "EmploymentStatusCode_08": "",
                                "StudentStatusCode_09": "",
                                "YesNoConditionOrResponseCode_10": "N",
                                "DateTimePeriodFormatQualifier_11": null,
                                "DateTimePeriod_12": null,
                                "ConfidentialityCode_13": null,
                                "CityName_14": null,
                                "StateOrProvinceCode_15": null,
                                "CountryCode_16": null,
                                "BirthSequenceNumber_17": null
                            },
                            "AllREF": {
                                "REF_SubscriberIdentifier": {
                                    "ReferenceIdentificationQualifier_01": "0F",
                                    "MemberGroupOrPolicyNumber_02": "001022763",
                                    "Description_03": null,
                                    "ReferenceIdentifier_04": {
                                        "ReferenceIdentificationQualifier_01": null,
                                        "ReferenceIdentification_02": null,
                                        "ReferenceIdentificationQualifier_03": null,
                                        "ReferenceIdentification_04": null,
                                        "ReferenceIdentificationQualifier_05": null,
                                        "ReferenceIdentification_06": null
                                    }
                                },
                                "REF_MemberPolicyNumber": {
                                    "ReferenceIdentificationQualifier_01": "1L",
                                    "MemberGroupOrPolicyNumber_02": "10091",
                                    "Description_03": null,
                                    "ReferenceIdentifier_04": {
                                        "ReferenceIdentificationQualifier_01": null,
                                        "ReferenceIdentification_02": null,
                                        "ReferenceIdentificationQualifier_03": null,
                                        "ReferenceIdentification_04": null,
                                        "ReferenceIdentificationQualifier_05": null,
                                        "ReferenceIdentification_06": null
                                    }
                                },
                                "REF_MemberSupplementalIdentifier": [
                                    {
                                        "ReferenceIdentificationQualifier_01": "23",
                                        "MemberGroupOrPolicyNumber_02": "ME0990214",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    },
                                    {
                                        "ReferenceIdentificationQualifier_01": "DX",
                                        "MemberGroupOrPolicyNumber_02": "1000",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    },
                                    {
                                        "ReferenceIdentificationQualifier_01": "ZZ",
                                        "MemberGroupOrPolicyNumber_02": "03",
                                        "Description_03": null,
                                        "ReferenceIdentifier_04": {
                                            "ReferenceIdentificationQualifier_01": null,
                                            "ReferenceIdentification_02": null,
                                            "ReferenceIdentificationQualifier_03": null,
                                            "ReferenceIdentification_04": null,
                                            "ReferenceIdentificationQualifier_05": null,
                                            "ReferenceIdentification_06": null
                                        }
                                    }
                                ]
                            },
                            "DTP_MemberLevelDates": [
                                {
                                    "DateTimeQualifier_01": "356",
                                    "DateTimePeriodFormatQualifier_02": "D8",
                                    "DateTimePeriod_03": "20220901"
                                }
                            ],
                            "AllNM1": {
                                "Loop2100A": {
                                    "NM1_MemberName": {
                                        "EntityIdentifierCode_01": "IL",
                                        "EntityTypeQualifier_02": "1",
                                        "ResponseContactLastOrOrganizationName_03": "BROWN",
                                        "ResponseContactFirstName_04": "JASON",
                                        "ResponseContactMiddleName_05": "M",
                                        "NamePrefix_06": "",
                                        "ResponseContactNameSuffix_07": "",
                                        "IdentificationCodeQualifier_08": "34",
                                        "ResponseContactIdentifier_09": "854726481",
                                        "EntityRelationshipCode_10": null,
                                        "EntityIdentifierCode_11": null,
                                        "NameLastOrOrganizationName_12": null
                                    },
                                    "PER_MemberCommunicationsNumbers": null,
                                    "N3_MemberResidenceStreetAddress": {
                                        "ResponseContactAddressLine_01": "214 BUTTERMILK LANE",
                                        "ResponseContactAddressLine_02": null
                                    },
                                    "N4_MemberCity_State_ZIPCode": {
                                        "AdditionalPatientInformationContactCityName_01": "ITHICA",
                                        "AdditionalPatientInformationContactStateCode_02": "NY",
                                        "AdditionalPatientInformationContactPostalZoneOrZIPCode_03": "14850",
                                        "CountryCode_04": null,
                                        "LocationQualifier_05": null,
                                        "LocationIdentifier_06": null,
                                        "CountrySubdivisionCode_07": null
                                    },
                                    "DMG_MemberDemographics": {
                                        "DateTimePeriodFormatQualifier_01": "D8",
                                        "DependentBirthDate_02": "19990412",
                                        "DependentGenderCode_03": "M",
                                        "MaritalStatusCode_04": null,
                                        "CompositeRaceOrEthnicityInformation_05": [
                                            {
                                                "RaceOrEthnicityCode_01": null,
                                                "CodeListQualifierCode_02": null,
                                                "IndustryCode_03": null
                                            }
                                        ],
                                        "CitizenshipStatusCode_06": null,
                                        "CountryCode_07": null,
                                        "BasisOfVerificationCode_08": null,
                                        "Quantity_09": null,
                                        "CodeListQualifierCode_10": null,
                                        "IndustryCode_11": null
                                    },
                                    "EC_EmploymentClass": [],
                                    "ICM_MemberIncome": null,
                                    "AMT_MemberPolicyAmounts": [],
                                    "HLH_MemberHealthInformation": null,
                                    "LUI_MemberLanguage": []
                                },
                                "Loop2100B": null,
                                "Loop2100C": null,
                                "Loop2100D": [],
                                "Loop2100E": [],
                                "Loop2100F": null,
                                "Loop2100G": [],
                                "Loop2100H": null
                            },
                            "Loop2200": [],
                            "Loop2300": [
                                {
                                    "HD_HealthCoverage": {
                                        "MaintenanceTypeCode_01": "030",
                                        "MaintenanceReasonCode_02": "",
                                        "InsuranceLineCode_03": "PDG",
                                        "PlanCoverageDescription_04": "MVPG",
                                        "CoverageLevelCode_05": null,
                                        "Count_06": null,
                                        "Count_07": null,
                                        "UnderwritingDecisionCode_08": null,
                                        "LateEnrollmentIndicator_09": null,
                                        "DrugHouseCode_10": null,
                                        "YesNoConditionOrResponseCode_11": null
                                    },
                                    "DTP_HealthCoverageDates": [
                                        {
                                            "DateTimeQualifier_01": "348",
                                            "DateTimePeriodFormatQualifier_02": "D8",
                                            "DateTimePeriod_03": "20220901"
                                        }
                                    ],
                                    "AMT_HealthCoveragePolicy": [],
                                    "AllREF": {
                                        "REF_HealthCoveragePolicyNumber": [],
                                        "REF_PriorCoverageMonths": null
                                    },
                                    "IDC_IdentificationCard": [],
                                    "Loop2310": [],
                                    "Loop2320": []
                                },
                                {
                                    "HD_HealthCoverage": {
                                        "MaintenanceTypeCode_01": "030",
                                        "MaintenanceReasonCode_02": "",
                                        "InsuranceLineCode_03": "HLT",
                                        "PlanCoverageDescription_04": "MVPG",
                                        "CoverageLevelCode_05": null,
                                        "Count_06": null,
                                        "Count_07": null,
                                        "UnderwritingDecisionCode_08": null,
                                        "LateEnrollmentIndicator_09": null,
                                        "DrugHouseCode_10": null,
                                        "YesNoConditionOrResponseCode_11": null
                                    },
                                    "DTP_HealthCoverageDates": [
                                        {
                                            "DateTimeQualifier_01": "348",
                                            "DateTimePeriodFormatQualifier_02": "D8",
                                            "DateTimePeriod_03": "20230101"
                                        }
                                    ],
                                    "AMT_HealthCoveragePolicy": [],
                                    "AllREF": {
                                        "REF_HealthCoveragePolicyNumber": [],
                                        "REF_PriorCoverageMonths": null
                                    },
                                    "IDC_IdentificationCard": [],
                                    "Loop2310": [],
                                    "Loop2320": []
                                }
                            ],
                            "LoopLS": null
                        }
                    ],
                    "SE": {
                        "NumberOfIncludedSegments_01": "53",
                        "TransactionSetControlNumber_02": "000000001"
                    }
                }
            ],
            "GETrailers": [
                {
                    "NumberOfIncludedSets_01": "1",
                    "GroupControlNumber_02": "682952406"
                }
            ]
        }
    ],
    "IEATrailers": [
        {
            "NumberOfIncludedGroups_01": "1",
            "InterchangeControlNumber_02": "682952406"
        }
    ]
}
```
