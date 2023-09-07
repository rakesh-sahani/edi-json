package com.edi.service.Impl;

import com.edi.model.x12.EDI834;
import com.edi.model.x12.Segment;
import com.edi.model.x12.x834.*;
import com.edi.service.EdiService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class EdiServiceImpl implements EdiService {
    @Override
    public EDI834 getEdiX834(FileInputStream fileInputStream) throws IOException {
        EDI834 edi834 = new EDI834();
        edi834.setSegmentDelimiter("~");
        edi834.setDataElementDelimiter("*");
        String readableString = fileStreamReader(fileInputStream);
        String ediData = regexAdd(readableString);
        if (!ediData.isEmpty()) {
            if (ediData.startsWith("#-#-#ISA")) {
                LinkedList<String> linkedValueList = gsSplit(ediData);
                LinkedList<IEATrailers> ieaTrailersList = new LinkedList<>();
                List<Groups> groupsList = new LinkedList<>();
                for (String listValue : linkedValueList) {
                    if (listValue.startsWith("#-#-#ISA")) {
                        edi834.setIsa(isa(listValue));
                    }
                    if (listValue.startsWith("#-#-#GS")) {
                        LinkedList<String> gsLinkedList = iaeSplit(listValue);
                        for (String gsListValue : gsLinkedList) {
                            if (gsListValue.startsWith("#-#-#GS")) {
                                groupsList.add(groups(gsListValue));
                            }
                            if (gsListValue.startsWith("#-#-#IEA")) {
                                ieaTrailersList.add(ieaTrailers(gsListValue));
                            }
                        }
                    }
                    edi834.setGroups(groupsList);
                    edi834.setIeaTrailers(ieaTrailersList);
                }

            }
        }
        return edi834;
    }

    private String fileStreamReader(FileInputStream fileInputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        int ascii;
        StringBuilder readableFormat = new StringBuilder();
        // Use of read() method
        while ((ascii = inputStreamReader.read()) != -1) {
            readableFormat.append((char) ascii);
        }
        String ediString = "";
        if (readableFormat.toString().contains("~")) {
            ediString = readableFormat.toString().replaceAll("\r\n", "");
        } else {
            ediString = readableFormat.toString().replaceAll("\n", "~");
        }
        return ediString;
    }

    private String regexAdd(String dataString) {
        String[] segments = dataString.split("~");
        StringBuilder segmentString = new StringBuilder();
        for (String value : segments) {
            segmentString.append("#-#-#").append(value).append("~");
        }
        return segmentString.toString();
    }

    private ISA isa(String isaString) {
        Segment segment = stringToMap(isaString);
        return mapISA(segment.getSegmentMap());
    }

    private Groups groups(String groupString) {
        List<Transactions> transactionsList = new LinkedList<>();
        List<GETrailers> geTrailersList = new LinkedList<>();
        Groups groups = new Groups();
        LinkedList<String> linkedValueList = stSplit(groupString);
        for (String gsListValue : linkedValueList) {
            if (gsListValue.startsWith("#-#-#GS")) {
                groups.setGs(gs(gsListValue));
            }
            if (gsListValue.startsWith("#-#-#ST")) {
                transactionsList.add(transactions(gsListValue));
            }
            if (gsListValue.startsWith("#-#-#GE")) {
                geTrailersList.add(geTrailers(gsListValue));
            }
        }
        groups.setTransactions(transactionsList);
        groups.setGeTrailers(geTrailersList);
        return groups;
    }

    private Transactions transactions(String transactionsString) {
        Transactions transactions = new Transactions();
        LinkedList<String> linkedValueList = seSplit(transactionsString);
        List<DTP_FileEffectiveDate> dtpFileEffectiveDates = new LinkedList<>();
        List<QTY_TransactionSetControlTotals> qtyTransactionSetControlTotals = new LinkedList<>();
        AllN1 allN1 = new AllN1();
        List<Loop1000C> loop1000CList = new LinkedList<>();
        List<Loop2000> loop2000List = new LinkedList<>();
        for (String value : linkedValueList) {
            if (value.startsWith("#-#-#ST")) {
                transactions.setSt(st(value));
            }
            if (value.startsWith("#-#-#BGN")) {
                transactions.setBgnBeginningSegment(bgn(value));
            }
            if (value.startsWith("#-#-#REF")) {
                transactions.setRefTransactionSetPolicyNumber(refTransactionSetPolicyNumber(value));
            }
            if (value.startsWith("#-#-#DTP")) {
                dtpFileEffectiveDates.add(dtpFileEffectiveDate(value));
            }
            if (value.startsWith("#-#-#QTY")) {
                qtyTransactionSetControlTotals.add(qtyTransactionSetControlTotals(value));
            }
            if (value.startsWith("#-#-#N1")) {
                String[] n1Value = value.split("\\*");
                switch (n1Value[1]) {
                    case "P5" -> allN1.setLoop1000A(loop1000A(value));
                    case "IN" -> allN1.setLoop1000B(loop1000B(value));
                    case "BO", "TV" -> loop1000CList.add(loop1000C(value));
                }
            }
            if (value.startsWith("#-#-#INS")) {
                loop2000List.add(loop2000(value));
            }
            if (value.startsWith("#-#-#SE")) {
                transactions.setSe(se(value));
            }
        }
        allN1.setLoop1000C(loop1000CList);
        transactions.setDtpFileEffectiveDates(dtpFileEffectiveDates);
        transactions.setQtyTransactionSetControlTotals(qtyTransactionSetControlTotals);
        transactions.setAllN1(allN1);
        transactions.setLoop2000(loop2000List);
        return transactions;
    }

    private GS gs(String gsString) {
        Segment segment = stringToMap(gsString);
        return mapGS(segment.getSegmentMap());
    }

    private GETrailers geTrailers(String geTrailersString) {
        Segment segment = stringToMap(geTrailersString);
        return mapGETrailers(segment.getSegmentMap());
    }

    private IEATrailers ieaTrailers(String ieaString) {
        Segment segment = stringToMap(ieaString);
        return mapIEA(segment.getSegmentMap());
    }

    private ST st(String stString) {
        Segment segment = stringToMap(stString);
        return mapST(segment.getSegmentMap());
    }

    private SE se(String seString) {
        Segment segment = stringToMap(seString);
        return mapSE(segment.getSegmentMap());
    }

    private BGN_BeginningSegment bgn(String seString) {
        Segment segment = stringToMap(seString);
        return mapBGN(segment.getSegmentMap());
    }

    private REF_TransactionSetPolicyNumber refTransactionSetPolicyNumber(String refString) {
        Segment segment = stringToMap(refString);
        return mapRefTransactionSetPolicyNumber(segment.getSegmentMap());
    }

    private DTP_FileEffectiveDate dtpFileEffectiveDate(String dtpString) {
        Segment segment = stringToMap(dtpString);
        return mapDTPFileEffectiveDate(segment.getSegmentMap());
    }

    private DTP_ReportingCategoryDate dtpReportingCategoryDate(String dtpString) {
        Segment segment = stringToMap(dtpString);
        return mapDtpReportingCategoryDate(segment.getSegmentMap());
    }

    private QTY_TransactionSetControlTotals qtyTransactionSetControlTotals(String qtyString) {
        Segment segment = stringToMap(qtyString);
        return mapQtyTransactionSetControlTotals(segment.getSegmentMap());
    }

    private Loop1000A loop1000A(String n1String) {
        return Loop1000A.builder().n1SponsorName(n1SponsorName(n1String)).build();
    }

    private N1_SponsorName n1SponsorName(String n1String) {
        Segment segment = stringToMap(n1String);
        return mapN1SponsorName(segment.getSegmentMap());
    }

    private Loop1000B loop1000B(String n1String) {
        return Loop1000B.builder().n1Payer(n1Payer(n1String)).build();
    }

    private N1_Payer n1Payer(String n1String) {
        Segment segment = stringToMap(n1String);
        return mapN1Payer(segment.getSegmentMap());
    }

    private Loop1000C loop1000C(String n1String) {
        Loop1000C loop1000C = new Loop1000C();
        if (n1String.startsWith("#-#-#N1") && n1String.contains("#-#-#ACT")) {
            LinkedList<String> stringLinkedList = actSplit(n1String);
            loop1000C.setN1TpaBrokerName(n1TpaBrokerName(stringLinkedList.get(0)));
            loop1000C.setLoop1100C(loop1100C(stringLinkedList.get(1)));
        } else if (n1String.startsWith("#-#-#N1")) {
            loop1000C.setN1TpaBrokerName(n1TpaBrokerName(n1String));
        }
        return loop1000C;
    }

    private N1_TPA_BrokerName n1TpaBrokerName(String n1String) {
        Segment segment = stringToMap(n1String);
        return mapN1TpaBrokerName(segment.getSegmentMap());
    }

    private N1_ReportingCategory n1ReportingCategory(String n1String) {
        Segment segment = stringToMap(n1String);
        return mapN1ReportingCategory(segment.getSegmentMap());
    }

    private Loop1100C loop1100C(String n1String) {
        return Loop1100C.builder()
                .actTpaBrokerAccountInformation(actTpaBrokerAccountInformation(n1String)).build();
    }

    private ACT_TPA_BrokerAccountInformation actTpaBrokerAccountInformation(String actString) {
        Segment segment = stringToMap(actString);
        return mapActTpaBrokerAccountInformation(segment.getSegmentMap());
    }

    private Loop2000 loop2000(String ins) {
        Loop2000 loop2000 = new Loop2000();
        AllREF allREF = new AllREF();
        AllNM1 allNM1 = new AllNM1();
        List<Loop2100D> loop2100DList = new LinkedList<>();
        List<Loop2100E> loop2100EList = new LinkedList<>();
        List<Loop2100G> loop2100GList = new LinkedList<>();
        List<REF_MemberSupplementalIdentifier> refMemberSupplementalIdentifierList = new LinkedList<>();
        List<DTP_MemberLevelDates> dtpMemberLevelDatesList = new LinkedList<>();
        List<Loop2200> loop2200List = new LinkedList<>();
        List<Loop2300> loop2300List = new LinkedList<>();
        LinkedList<String> lsValueList = lsSplit(ins);
        LinkedList<String> hdValueList = hdSplit(lsValueList);
        LinkedList<String> dsbValueList = dsbSplit(hdValueList);
        LinkedList<String> nm1ValueList = nm1Split(dsbValueList);
        LinkedList<String> linkedValueList = loop2000Split(nm1ValueList);
        for (String value : linkedValueList) {
            if (value.startsWith("#-#-#INS")) {
                loop2000.setInsMemberLevelDetail(insMemberLevelDetail(value));
            }
            if (value.startsWith("#-#-#REF")) {
                String[] refValue = value.split("\\*");
                switch (refValue[1]) {
                    case "0F" -> allREF.setRefSubscriberIdentifier(refSubscriberIdentifier(value));
                    case "1L" -> allREF.setRefMemberPolicyNumber(refMemberPolicyNumber(value));
                    case "17", "23", "3H", "DX", "F6", "Q4", "ZZ" ->
                            refMemberSupplementalIdentifierList.add(refMemberSupplementalIdentifier(value));
                }
            }
            if (value.startsWith("#-#-#DTP")) {
                dtpMemberLevelDatesList.add(dtpMemberLevelDates(value));
            }
            if (value.startsWith("#-#-#NM1")) {
                String[] nm1Value = value.split("\\*");
                switch (nm1Value[1]) {
                    case "74", "IL" -> allNM1.setLoop2100A(loop2100A(value));
                    case "70" -> allNM1.setLoop2100B(loop2100B(value));
                    case "31" -> allNM1.setLoop2100C(loop2100C(value));
                    case "ES" -> loop2100DList.add(loop2100D(value));
                    case "M8" -> loop2100EList.add(loop2100E(value));
                    case "S3" -> allNM1.setLoop2100F(loop2100F(value));
                    case "E1", "EI", "EXS", "GD", "J6", "QD" -> loop2100GList.add(loop2100G(value));
                    default -> allNM1.setLoop2100H(loop2100H(value));
                }
            }
            if (value.startsWith("#-#-#DSB")) {
                loop2200List.add(loop2200(value));
            }
            if (value.startsWith("#-#-#HD")) {
                loop2300List.add(loop2300(value));
            }
            if (value.startsWith("#-#-#LS")) {
                loop2000.setLoopLS(loopLS(value));
            }
        }
        allNM1.setLoop2100D(loop2100DList);
        allNM1.setLoop2100E(loop2100EList);
        allNM1.setLoop2100G(loop2100GList);
        allREF.setRefMemberSupplementalIdentifier(refMemberSupplementalIdentifierList);
        loop2000.setAllREF(allREF);
        loop2000.setDtpMemberLevelDates(dtpMemberLevelDatesList);
        loop2000.setAllNM1(allNM1);
        loop2000.setLoop2200(loop2200List);
        loop2000.setLoop2300(loop2300List);
        return loop2000;
    }

    private LinkedList<String> lsSplit(String ins) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        if (ins.contains("#-#-#LS")) {
            String[] split = ins.split("#-#-#LS");
            for (String value : split) {
                if (value.startsWith("#-#-#INS")) {
                    stringLinkedList.add(value);
                } else {
                    String lsAdded = "#-#-#LS" + value;
                    stringLinkedList.add(lsAdded);
                }
            }
        } else {
            stringLinkedList.add(ins);
        }
        return stringLinkedList;
    }

    private LinkedList<String> hdSplit(LinkedList<String> insList) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (String insValue : insList) {
            if (insValue.contains("#-#-#HD")) {
                String[] split = insValue.split("#-#-#HD");
                for (String value : split) {
                    if (value.startsWith("#-#-#INS")) {
                        stringLinkedList.add(value);
                    } else {
                        String hdAdded = "#-#-#HD" + value;
                        stringLinkedList.add(hdAdded);
                    }
                }
            } else {
                stringLinkedList.add(insValue);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> dsbSplit(LinkedList<String> hdList) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (String hdValue : hdList) {
            if (hdValue.contains("#-#-#DSB")) {
                String[] split = hdValue.split("#-#-#DSB");
                for (String value : split) {
                    if (value.startsWith("#-#-#INS")) {
                        stringLinkedList.add(value);
                    } else {
                        String hdAdded = "#-#-#DSB" + value;
                        stringLinkedList.add(hdAdded);
                    }
                }
            } else {
                stringLinkedList.add(hdValue);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> nm1Split(LinkedList<String> dsbList) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (String dsbValue : dsbList) {
            if (dsbValue.contains("#-#-#NM1")) {
                String[] split = dsbValue.split("#-#-#NM1");
                for (String value : split) {
                    if (value.startsWith("#-#-#INS")) {
                        stringLinkedList.add(value);
                    } else {
                        String hdAdded = "#-#-#NM1" + value;
                        stringLinkedList.add(hdAdded);
                    }
                }
            } else {
                stringLinkedList.add(dsbValue);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> loop2000Split(LinkedList<String> insList) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        LinkedList<String> insSplitList;
        for (String insValue : insList) {
            if (insValue.contains("#-#-#INS")) {
                if (insValue.startsWith("#-#-#INS")) {
                    insSplitList = tiltSplit(insValue);
                    stringLinkedList.addAll(insSplitList);
                }
            } else {
                stringLinkedList.add(insValue);
            }
        }
        return stringLinkedList;
    }

    private INS_MemberLevelDetail insMemberLevelDetail(String insString) {
        Segment segment = stringToMap(insString);
        return mapInsMemberLevelDetail(segment.getSegmentMap());
    }

    private REF_SubscriberIdentifier refSubscriberIdentifier(String refString) {
        Segment segment = stringToMap(refString);
        return mapREFSubscriberIdentifier(segment.getSegmentMap());
    }

    private REF_MemberPolicyNumber refMemberPolicyNumber(String refString) {
        Segment segment = stringToMap(refString);
        return mapREFMemberPolicyNumber(segment.getSegmentMap());
    }

    private REF_MemberSupplementalIdentifier refMemberSupplementalIdentifier(String refString) {
        Segment segment = stringToMap(refString);
        return mapREFMemberSupplementalIdentifier(segment.getSegmentMap());
    }

    private REF_HealthCoveragePolicyNumber refHealthCoveragePolicyNumber(String refString) {
        Segment segment = stringToMap(refString);
        return mapRefHealthCoveragePolicyNumber(segment.getSegmentMap());
    }

    private REF_AdditionalCoordinationOfBenefitsIdentifiers refAdditionalCoordinationOfBenefitsIdentifiers(String refString) {
        Segment segment = stringToMap(refString);
        return mapRefAdditionalCoordinationOfBenefitsIdentifiers(segment.getSegmentMap());
    }

    private REF_ReportingCategoryReference refReportingCategoryReference(String refString) {
        Segment segment = stringToMap(refString);
        return mapRefReportingCategoryReference(segment.getSegmentMap());
    }

    private DTP_MemberLevelDates dtpMemberLevelDates(String dtpString) {
        Segment segment = stringToMap(dtpString);
        return mapDTPMemberLevelDates(segment.getSegmentMap());
    }

    private DTP_CoordinationOfBenefitsEligibilityDates dtpCoordinationOfBenefitsEligibilityDates(String dtpString) {
        Segment segment = stringToMap(dtpString);
        return mapDtpCoordinationOfBenefitsEligibilityDates(segment.getSegmentMap());
    }

    private Loop2100A loop2100A(String loopString) {
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        Loop2100A loop2100A = new Loop2100A();
        List<EC_EmploymentClass> ecEmploymentClassList = new LinkedList<>();
        List<AMT_MemberPolicyAmounts> amtMemberPolicyAmountsList = new LinkedList<>();
        List<LUI_MemberLanguage> luiMemberLanguageList = new LinkedList<>();
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#NM1")) {
                loop2100A.setNm1MemberName(nm1MemberName(loopValue));
            }
            if (loopValue.startsWith("#-#-#PER")) {
                loop2100A.setPerMemberCommunicationsNumbers(perMemberCommunicationsNumbers(loopValue));
            }
            if (loopValue.startsWith("#-#-#N3")) {
                loop2100A.setN3MemberResidenceStreetAddress(n3MemberResidenceStreetAddress(loopValue));
            }
            if (loopValue.startsWith("#-#-#N4")) {
                loop2100A.setN4MemberCityStateZipCode(n4MemberCityStateZipCode(loopValue));
            }
            if (loopValue.startsWith("#-#-#DMG")) {
                loop2100A.setDmgMemberDemographics(dmgMemberDemographics(loopValue));
            }
            if (loopValue.startsWith("#-#-#EC")) {
                ecEmploymentClassList.add(ecEmploymentClass(loopValue));
            }
            if (loopValue.startsWith("#-#-#ICM")) {
                loop2100A.setIcmMemberIncome(icmMemberIncome(loopValue));
            }
            if (loopValue.startsWith("#-#-#AMT")) {
                amtMemberPolicyAmountsList.add(amtMemberPolicyAmounts(loopValue));
            }
            if (loopValue.startsWith("#-#-#HLH")) {
                loop2100A.setHlhMemberHealthInformation(hlhMemberHealthInformation(loopValue));
            }
            if (loopValue.startsWith("#-#-#LUI")) {
                luiMemberLanguageList.add(luiMemberLanguage(loopValue));
            }
        }
        loop2100A.setEcEmploymentClasses(ecEmploymentClassList);
        loop2100A.setAmtMemberPolicyAmounts(amtMemberPolicyAmountsList);
        loop2100A.setLuiMemberLanguages(luiMemberLanguageList);
        return loop2100A;
    }

    private Loop2100B loop2100B(String loopString) {
        Loop2100B loop2100B = new Loop2100B();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#NM1")) {
                loop2100B.setNm1IncorrectMemberName(nm1IncorrectMemberName(loopValue));
            }
            if (loopValue.startsWith("#-#-#DMG")) {
                loop2100B.setDmgIncorrectMemberDemographics(dmgIncorrectMemberDemographics(loopValue));
            }
        }
        return loop2100B;
    }

    private Loop2100C loop2100C(String loopString) {
        Loop2100C loop2100C = new Loop2100C();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#NM1")) {
                loop2100C.setNm1MemberMailingAddress(nm1MemberMailingAddress(loopValue));
            }
            if (loopValue.startsWith("#-#-#N3")) {
                loop2100C.setN3MemberMailStreetAddress(n3MemberMailStreetAddress(loopValue));
            }
            if (loopValue.startsWith("#-#-#N4")) {
                loop2100C.setN4MemberMailCityStateZipCode(n4MemberMailCityStateZipCode(loopValue));
            }
        }
        return loop2100C;
    }

    private Loop2100D loop2100D(String loopString) {
        Loop2100D loop2100D = new Loop2100D();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#NM1")) {
                loop2100D.setNm1MemberEmployer(nm1MemberEmployer(loopValue));
            }
            if (loopValue.startsWith("#-#-#PER")) {
                loop2100D.setPerMemberEmployerCommunicationsNumbers(perMemberEmployerCommunicationsNumbers(loopValue));
            }
            if (loopValue.startsWith("#-#-#N3")) {
                loop2100D.setN3MemberEmployerStreetAddress(n3MemberEmployerStreetAddress(loopValue));
            }
            if (loopValue.startsWith("#-#-#N4")) {
                loop2100D.setN4MemberEmployerCityStateZipCode(n4MemberEmployerCityStateZipCode(loopValue));
            }
        }
        return loop2100D;
    }

    private Loop2100E loop2100E(String loopString) {
        Loop2100E loop2100E = new Loop2100E();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#NM1")) {
                loop2100E.setNm1MemberSchool(nm1MemberSchool(loopValue));
            }
            if (loopValue.startsWith("#-#-#PER")) {
                loop2100E.setPerMemberSchoolCommunicationsNumbers(perMemberSchoolCommunicationsNumbers(loopValue));
            }
            if (loopValue.startsWith("#-#-#N3")) {
                loop2100E.setN3MemberSchoolStreetAddress(n3MemberSchoolStreetAddress(loopValue));
            }
            if (loopValue.startsWith("#-#-#N4")) {
                loop2100E.setN4MemberSchoolCityStateZipCode(n4MemberSchoolCityStateZipCode(loopValue));
            }
        }
        return loop2100E;
    }

    private Loop2100F loop2100F(String loopString) {
        Loop2100F loop2100F = new Loop2100F();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#NM1")) {
                loop2100F.setNm1CustodialParent(nm1CustodialParent(loopValue));
            }
            if (loopValue.startsWith("#-#-#PER")) {
                loop2100F.setPerCustodialParentCommunicationsNumbers(perCustodialParentCommunicationsNumbers(loopValue));
            }
            if (loopValue.startsWith("#-#-#N3")) {
                loop2100F.setN3CustodialParentStreetAddress(n3CustodialParentStreetAddress(loopValue));
            }
            if (loopValue.startsWith("#-#-#N4")) {
                loop2100F.setN4CustodialParentCityStateZipCode(n4CustodialParentCityStateZipCode(loopValue));
            }
        }
        return loop2100F;
    }

    private Loop2100G loop2100G(String loopString) {
        Loop2100G loop2100G = new Loop2100G();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#NM1")) {
                loop2100G.setNm1ResponsiblePerson(nm1ResponsiblePerson(loopValue));
            }
            if (loopValue.startsWith("#-#-#PER")) {
                loop2100G.setPerResponsiblePersonCommunicationsNumbers(perResponsiblePersonCommunicationsNumbers(loopValue));
            }
            if (loopValue.startsWith("#-#-#N3")) {
                loop2100G.setN3ResponsiblePersonStreetAddress(n3ResponsiblePersonStreetAddress(loopValue));
            }
            if (loopValue.startsWith("#-#-#N4")) {
                loop2100G.setN4ResponsiblePersonCityStateZipCode(n4ResponsiblePersonCityStateZipCode(loopValue));
            }
        }
        return loop2100G;
    }

    private Loop2100H loop2100H(String loopString) {
        Loop2100H loop2100H = new Loop2100H();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#NM1")) {
                loop2100H.setNm1DropOffLocation(nm1DropOffLocation(loopValue));
            }
            if (loopValue.startsWith("#-#-#N3")) {
                loop2100H.setN3DropOffLocationStreetAddress(n3DropOffLocationStreetAddress(loopValue));
            }
            if (loopValue.startsWith("#-#-#N4")) {
                loop2100H.setN4DropOffLocationCityStateZipCode(n4DropOffLocationCityStateZipCode(loopValue));
            }
        }
        return loop2100H;
    }

    private Loop2200 loop2200(String loopString) {
        Loop2200 loop2200 = new Loop2200();
        List<DTP_DisabilityEligibilityDates> dtpDisabilityEligibilityDatesList = new LinkedList<>();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String loopValue : loopSplitList) {
            if (loopValue.startsWith("#-#-#DSB")) {
                loop2200.setDsbDisabilityInformation(dsbDisabilityInformation(loopValue));
            }
            if (loopValue.startsWith("#-#-#DTP")) {
                dtpDisabilityEligibilityDatesList.add(dtpDisabilityEligibilityDates(loopValue));
            }
        }
        loop2200.setDtpDisabilityEligibilityDates(dtpDisabilityEligibilityDatesList);
        return loop2200;
    }

    private Loop2300 loop2300(String loopString) {
        Loop2300 loop2300 = new Loop2300();
        List<DTP_HealthCoverageDates> dtpHealthCoverageDatesList = new LinkedList<>();
        List<AMT_HealthCoveragePolicy> amtHealthCoveragePolicyList = new LinkedList<>();
        All_REF allREF = new All_REF();
        List<REF_HealthCoveragePolicyNumber> refHealthCoveragePolicyNumberList = new LinkedList<>();
        List<IDC_IdentificationCard> idcIdentificationCardList = new LinkedList<>();
        List<Loop2310> loop2310List = new LinkedList<>();
        List<Loop2320> loop2320List = new LinkedList<>();
        LinkedList<String> cobSplitList = cobSplit(loopString);
        LinkedList<String> valueList = lxSplit(cobSplitList);
        for (String loopValue : valueList) {
            if (loopValue.startsWith("#-#-#HD")) {
                LinkedList<String> hdSplitList = tiltSplit(loopString);
                for (String value : hdSplitList) {
                    if (value.startsWith("#-#-#HD")) {
                        loop2300.setHdHealthCoverage(hdHealthCoverage(value));
                    }
                    if (value.startsWith("#-#-#DTP")) {
                        dtpHealthCoverageDatesList.add(dtpHealthCoverageDates(value));
                    }
                    if (value.startsWith("#-#-#AMT")) {
                        amtHealthCoveragePolicyList.add(amtHealthCoveragePolicy(value));
                    }
                    if (value.startsWith("#-#-#REF")) {
                        String[] refValue = value.split("\\*");
                        switch (refValue[1]) {
                            case "17", "1L", "ZZ" ->
                                    refHealthCoveragePolicyNumberList.add(refHealthCoveragePolicyNumber(value));
                        }
                    }
                    if (value.startsWith("#-#-#IDC")) {
                        idcIdentificationCardList.add(idcIdentificationCard(value));
                    }
                }
            }
            if (loopValue.startsWith("#-#-#LX")) {
                loop2310List.add(loop2310(loopValue));
            }
            if (loopValue.startsWith("#-#-#COB")) {
                loop2320List.add(loop2320(loopValue));
            }
        }
        loop2300.setDtpHealthCoverageDates(dtpHealthCoverageDatesList);
        loop2300.setAmtHealthCoveragePolicies(amtHealthCoveragePolicyList);
        allREF.setRefHealthCoveragePolicyNumbers(refHealthCoveragePolicyNumberList);
        loop2300.setAllREF(allREF);
        loop2300.setIdcIdentificationCards(idcIdentificationCardList);
        loop2300.setLoop2310(loop2310List);
        loop2300.setLoop2320(loop2320List);
        return loop2300;
    }

    private LinkedList<String> lxSplit(LinkedList<String> lxList) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (String lxValue : lxList) {
            if (lxValue.contains("#-#-#LX")) {
                String[] split = lxValue.split("#-#-#LX");
                for (String value : split) {
                    if (value.startsWith("#-#-#HD")) {
                        stringLinkedList.add(value);
                    } else {
                        String lxAdded = "#-#-#LX" + value;
                        stringLinkedList.add(lxAdded);
                    }
                }
            } else {
                stringLinkedList.add(lxValue);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> cobSplit(String hdString) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        if (hdString.contains("#-#-#COB")) {
            String[] split = hdString.split("#-#-#COB");
            for (String value : split) {
                if (value.startsWith("#-#-#HD")) {
                    stringLinkedList.add(value);
                } else {
                    String cobAdded = "#-#-#COB" + value;
                    stringLinkedList.add(cobAdded);
                }
            }
        } else {
            stringLinkedList.add(hdString);
        }
        return stringLinkedList;
    }

    private Loop2310 loop2310(String loopString) {
        Loop2310 loop2310 = new Loop2310();
        List<N3_ProviderAddress> n3ProviderAddressList = new LinkedList<>();
        List<PER_ProviderCommunicationsNumbers> perProviderCommunicationsNumbersList = new LinkedList<>();
        LinkedList<String> lxSplitList = tiltSplit(loopString);
        for (String value : lxSplitList) {
            if (value.startsWith("#-#-#LX")) {
                loop2310.setLxProviderInformation(lxProviderInformation(value));
            }
            if (value.startsWith("#-#-#NM1")) {
                String[] nm1Value = value.split("\\*");
                switch (nm1Value[1]) {
                    case "3D", "OD", "P3", "QA", "QN", "Y2" -> loop2310.setNm1ProviderName(nm1ProviderName(value));
                }
            }
            if (value.startsWith("#-#-#N3")) {
                n3ProviderAddressList.add(n3ProviderAddress(value));
            }
            if (value.startsWith("#-#-#N4")) {
                loop2310.setN4ProviderCityStateZipCode(n4ProviderCityStateZipCode(value));
            }
            if (value.startsWith("#-#-#PER")) {
                perProviderCommunicationsNumbersList.add(perProviderCommunicationsNumbers(value));
            }
            if (value.startsWith("#-#-#PLA")) {
                loop2310.setPlaProviderChangeReason(plaProviderChangeReason(value));
            }
        }
        loop2310.setN3ProviderAddresses(n3ProviderAddressList);
        loop2310.setPerProviderCommunicationsNumbers(perProviderCommunicationsNumbersList);
        return loop2310;
    }

    private LinkedList<String> cobNm1Split(String hdString) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        if (hdString.contains("#-#-#NM1")) {
            String[] split = hdString.split("#-#-#NM1");
            for (String value : split) {
                if (value.startsWith("#-#-#COB")) {
                    stringLinkedList.add(value);
                } else {
                    String nm1Added = "#-#-#NM1" + value;
                    stringLinkedList.add(nm1Added);
                }
            }
        } else {
            stringLinkedList.add(hdString);
        }
        return stringLinkedList;
    }

    private Loop2320 loop2320(String loopString) {
        Loop2320 loop2320 = new Loop2320();
        List<REF_AdditionalCoordinationOfBenefitsIdentifiers> refAdditionalCoordinationOfBenefitsIdentifiersList = new LinkedList<>();
        List<DTP_CoordinationOfBenefitsEligibilityDates> dtpCoordinationOfBenefitsEligibilityDatesList = new LinkedList<>();
        List<Loop2330> loop2330List = new LinkedList<>();
        LinkedList<String> cobSplitList = cobNm1Split(loopString);
        for (String listValue : cobSplitList) {
            if (listValue.startsWith("#-#-#COB")) {
                LinkedList<String> splitList = tiltSplit(listValue);
                for (String value : splitList) {
                    if (value.startsWith("#-#-#COB")) {
                        loop2320.setCobCoordinationOfBenefits(cobCoordinationOfBenefits(value));
                    }
                    if (value.startsWith("#-#-#REF")) {
                        String[] refValue = value.split("\\*");
                        switch (refValue[1]) {
                            case "60", "6P", "A6", "SY", "ZZ" ->
                                    refAdditionalCoordinationOfBenefitsIdentifiersList.add(refAdditionalCoordinationOfBenefitsIdentifiers(value));
                        }
                    }
                    if (value.startsWith("#-#-#DTP")) {
                        dtpCoordinationOfBenefitsEligibilityDatesList.add(dtpCoordinationOfBenefitsEligibilityDates(value));
                    }
                }
            }
            if (listValue.startsWith("#-#-#NM1")) {
                loop2330List.add(loop2330(listValue));
            }

        }
        loop2320.setRefAdditionalCoordinationOfBenefitsIdentifiers(refAdditionalCoordinationOfBenefitsIdentifiersList);
        loop2320.setDtpCoordinationOfBenefitsEligibilityDates(dtpCoordinationOfBenefitsEligibilityDatesList);
        loop2320.setLoop2330(loop2330List);
        return loop2320;
    }

    private Loop2330 loop2330(String loopString) {
        Loop2330 loop2330 = new Loop2330();
        LinkedList<String> loopSplitList = tiltSplit(loopString);
        for (String value : loopSplitList) {
            if (value.startsWith("#-#-#NM1")) {
                loop2330.setNm1CoordinationOfBenefitsRelatedEntity(nm1CoordinationOfBenefitsRelatedEntity(value));
            }
            if (value.startsWith("#-#-#N3")) {
                loop2330.setN3CoordinationOfBenefitsRelatedEntityAddress(n3CoordinationOfBenefitsRelatedEntityAddress(value));
            }
            if (value.startsWith("#-#-#N4")) {
                loop2330.setN4CoordinationOfBenefitsOtherInsuranceCompanyCityStateZipCode(n4CoordinationOfBenefitsOtherInsuranceCompanyCityStateZipCode(value));
            }
            if (value.startsWith("#-#-#PER")) {
                loop2330.setPerAdministrativeCommunicationsContact(perAdministrativeCommunicationsContact(value));
            }
        }
        return loop2330;
    }

    private LoopLS loopLS(String loopString) {
        LoopLS loopLS = new LoopLS();
        LinkedList<String> leSpliList = leSplit(loopString);
        List<Loop2700> loop2700List = new LinkedList<>();
        for (String value : leSpliList) {
            if (value.startsWith("#-#-#LS")) {
                loopLS.setLsAdditionalReportingCategories(lsAdditionalReportingCategories(value));
            }
            if (value.startsWith("#-#-#LX")) {
                loop2700List.add(loop2700(value));
            }
            if (value.startsWith("#-#-#LE")) {
                loopLS.setLeAdditionalReportingCategoriesTermination(leAdditionalReportingCategoriesTermination(value));
            }
        }
        loopLS.setLoop2700(loop2700List);
        return loopLS;
    }

    private Loop2700 loop2700(String lxString) {
        Loop2700 loop2700 = new Loop2700();
        LinkedList<String> loopSplitList = lxN1Split(lxString);
        for (String value : loopSplitList) {
            if (value.startsWith("#-#-#LX")) {
                loop2700.setLxMemberReportingCategories(lxMemberReportingCategories(value));
            }
            if (value.startsWith("#-#-#N1")) {
                loop2700.setLoop2750(loop2750(value));
            }
        }
        return loop2700;
    }

    private Loop2750 loop2750(String lxString) {
        Loop2750 loop2750 = new Loop2750();
        LinkedList<String> loopSplitList = tiltSplit(lxString);
        for (String value : loopSplitList) {
            if (value.startsWith("#-#-#N1")) {
                loop2750.setN1ReportingCategory(n1ReportingCategory(value));
            }
            if (value.startsWith("#-#-#REF")) {
                loop2750.setRefReportingCategoryReference(refReportingCategoryReference(value));
            }
            if (value.startsWith("#-#-#DTP")) {
                loop2750.setDtpReportingCategoryDate(dtpReportingCategoryDate(value));
            }
        }
        return loop2750;
    }

    private LinkedList<String> leSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        LinkedList<String> leLXSplitList;
        String[] split = ediData.split("#-#-#LE");
        for (String value : split) {
            if (value.startsWith("#-#-#LS")) {
                if (value.contains("#-#-#LX")) {
                    leLXSplitList = leLXSplit(value);
                    stringLinkedList.addAll(leLXSplitList);
                } else {
                    stringLinkedList.add(value);
                }
            } else {
                String leAdded = "#-#-#LE" + value;
                stringLinkedList.add(leAdded);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> leLXSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        String[] split = ediData.split("#-#-#LX");
        for (String value : split) {
            if (value.startsWith("#-#-#LS")) {
                stringLinkedList.add(value);
            } else {
                String leAdded = "#-#-#LX" + value;
                stringLinkedList.add(leAdded);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> lxN1Split(String lxString) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        String[] split = lxString.split("#-#-#N1");
        for (String value : split) {
            if (value.startsWith("#-#-#LX")) {
                stringLinkedList.add(value);
            } else {
                String n1Added = "#-#-#N1" + value;
                stringLinkedList.add(n1Added);
            }
        }
        return stringLinkedList;
    }

    private NM1_MemberName nm1MemberName(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1MemberName(segment.getSegmentMap());
    }

    private NM1_IncorrectMemberName nm1IncorrectMemberName(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1IncorrectMemberName(segment.getSegmentMap());
    }

    private NM1_MemberMailingAddress nm1MemberMailingAddress(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1MemberMailingAddress(segment.getSegmentMap());
    }

    private NM1_MemberEmployer nm1MemberEmployer(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1MemberEmployer(segment.getSegmentMap());
    }

    private NM1_MemberSchool nm1MemberSchool(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1MemberSchool(segment.getSegmentMap());
    }

    private NM1_CustodialParent nm1CustodialParent(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1CustodialParent(segment.getSegmentMap());
    }

    private NM1_ResponsiblePerson nm1ResponsiblePerson(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1ResponsiblePerson(segment.getSegmentMap());
    }

    private NM1_DropOffLocation nm1DropOffLocation(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1DropOffLocation(segment.getSegmentMap());
    }

    private NM1_ProviderName nm1ProviderName(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1ProviderName(segment.getSegmentMap());
    }

    private NM1_CoordinationOfBenefitsRelatedEntity nm1CoordinationOfBenefitsRelatedEntity(String nm1String) {
        Segment segment = stringToMap(nm1String);
        return mapNm1CoordinationOfBenefitsRelatedEntity(segment.getSegmentMap());
    }

    private PER_MemberCommunicationsNumbers perMemberCommunicationsNumbers(String perString) {
        Segment segment = stringToMap(perString);
        return mapPerMemberCommunicationsNumbers(segment.getSegmentMap());
    }

    private PER_MemberEmployerCommunicationsNumbers perMemberEmployerCommunicationsNumbers(String perString) {
        Segment segment = stringToMap(perString);
        return mapPerMemberEmployerCommunicationsNumbers(segment.getSegmentMap());
    }

    private PER_MemberSchoolCommunicationsNumbers perMemberSchoolCommunicationsNumbers(String perString) {
        Segment segment = stringToMap(perString);
        return mapPerMemberSchoolCommunicationsNumbers(segment.getSegmentMap());
    }

    private PER_CustodialParentCommunicationsNumbers perCustodialParentCommunicationsNumbers(String perString) {
        Segment segment = stringToMap(perString);
        return mapPerCustodialParentCommunicationsNumbers(segment.getSegmentMap());
    }

    private PER_ResponsiblePersonCommunicationsNumbers perResponsiblePersonCommunicationsNumbers(String perString) {
        Segment segment = stringToMap(perString);
        return mapPerResponsiblePersonCommunicationsNumbers(segment.getSegmentMap());
    }

    private PER_ProviderCommunicationsNumbers perProviderCommunicationsNumbers(String perString) {
        Segment segment = stringToMap(perString);
        return mapPerProviderCommunicationsNumbers(segment.getSegmentMap());
    }

    private PER_AdministrativeCommunicationsContact perAdministrativeCommunicationsContact(String perString) {
        Segment segment = stringToMap(perString);
        return mapPerAdministrativeCommunicationsContact(segment.getSegmentMap());
    }

    private N3_MemberResidenceStreetAddress n3MemberResidenceStreetAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3MemberResidenceStreetAddress(segment.getSegmentMap());
    }

    private N3_MemberMailStreetAddress n3MemberMailStreetAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3MemberMailStreetAddress(segment.getSegmentMap());
    }

    private N3_MemberEmployerStreetAddress n3MemberEmployerStreetAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3MemberEmployerStreetAddress(segment.getSegmentMap());
    }

    private N3_MemberSchoolStreetAddress n3MemberSchoolStreetAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3MemberSchoolStreetAddress(segment.getSegmentMap());
    }

    private N3_CustodialParentStreetAddress n3CustodialParentStreetAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3CustodialParentStreetAddress(segment.getSegmentMap());
    }

    private N3_ResponsiblePersonStreetAddress n3ResponsiblePersonStreetAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3ResponsiblePersonStreetAddress(segment.getSegmentMap());
    }

    private N3_DropOffLocationStreetAddress n3DropOffLocationStreetAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3DropOffLocationStreetAddress(segment.getSegmentMap());
    }

    private N3_ProviderAddress n3ProviderAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3ProviderAddress(segment.getSegmentMap());
    }

    private N3_CoordinationOfBenefitsRelatedEntityAddress n3CoordinationOfBenefitsRelatedEntityAddress(String n3String) {
        Segment segment = stringToMap(n3String);
        return mapN3CoordinationOfBenefitsRelatedEntityAddress(segment.getSegmentMap());
    }

    private N4_MemberCity_State_ZIPCode n4MemberCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4MemberCityStateZipCode(segment.getSegmentMap());
    }

    private N4_MemberMailCity_State_ZIPCode n4MemberMailCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4MemberMailCityStateZipCode(segment.getSegmentMap());
    }

    private N4_MemberEmployerCity_State_ZIPCode n4MemberEmployerCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4MemberEmployerCityStateZipCode(segment.getSegmentMap());
    }


    private N4_MemberSchoolCity_State_ZIPCode n4MemberSchoolCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4MemberSchoolCityStateZipCode(segment.getSegmentMap());
    }

    private N4_CustodialParentCity_State_ZIPCode n4CustodialParentCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4CustodialParentCityStateZipCode(segment.getSegmentMap());
    }

    private N4_ResponsiblePersonCity_State_ZIPCode n4ResponsiblePersonCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4ResponsiblePersonCityStateZipCode(segment.getSegmentMap());
    }

    private N4_DropOffLocationCity_State_ZIPCode n4DropOffLocationCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4DropOffLocationCityStateZipCode(segment.getSegmentMap());
    }

    private N4_ProviderCity_State_ZIPCode n4ProviderCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4ProviderCityStateZipCode(segment.getSegmentMap());
    }

    private N4_CoordinationOfBenefitsOtherInsuranceCompanyCity_State_ZIPCode n4CoordinationOfBenefitsOtherInsuranceCompanyCityStateZipCode(String n4String) {
        Segment segment = stringToMap(n4String);
        return mapN4CoordinationOfBenefitsOtherInsuranceCompanyCityStateZipCode(segment.getSegmentMap());
    }

    private DMG_MemberDemographics dmgMemberDemographics(String dmgString) {
        Segment segment = stringToMap(dmgString);
        return mapDmgMemberDemographics(segment.getSegmentMap());
    }

    private DMG_IncorrectMemberDemographics dmgIncorrectMemberDemographics(String dmgString) {
        Segment segment = stringToMap(dmgString);
        return mapDmgIncorrectMemberDemographics(segment.getSegmentMap());
    }

    private EC_EmploymentClass ecEmploymentClass(String ecString) {
        Segment segment = stringToMap(ecString);
        return mapEcEmploymentClass(segment.getSegmentMap());
    }

    private ICM_MemberIncome icmMemberIncome(String icmString) {
        Segment segment = stringToMap(icmString);
        return mapIcmMemberIncome(segment.getSegmentMap());
    }

    private AMT_MemberPolicyAmounts amtMemberPolicyAmounts(String amtString) {
        Segment segment = stringToMap(amtString);
        return mapAmtMemberPolicyAmounts(segment.getSegmentMap());
    }

    private HLH_MemberHealthInformation hlhMemberHealthInformation(String hlhString) {
        Segment segment = stringToMap(hlhString);
        return mapHlhMemberHealthInformation(segment.getSegmentMap());
    }


    private LUI_MemberLanguage luiMemberLanguage(String luiString) {
        Segment segment = stringToMap(luiString);
        return mapLuiMemberLanguage(segment.getSegmentMap());
    }

    private DSB_DisabilityInformation dsbDisabilityInformation(String luiString) {
        Segment segment = stringToMap(luiString);
        return mapDsbDisabilityInformation(segment.getSegmentMap());
    }

    private DTP_DisabilityEligibilityDates dtpDisabilityEligibilityDates(String luiString) {
        Segment segment = stringToMap(luiString);
        return mapDtpDisabilityEligibilityDates(segment.getSegmentMap());
    }

    private DTP_HealthCoverageDates dtpHealthCoverageDates(String luiString) {
        Segment segment = stringToMap(luiString);
        return mapDtpHealthCoverageDates(segment.getSegmentMap());
    }

    private AMT_HealthCoveragePolicy amtHealthCoveragePolicy(String luiString) {
        Segment segment = stringToMap(luiString);
        return mapAmtHealthCoveragePolicy(segment.getSegmentMap());
    }

    private HD_HealthCoverage hdHealthCoverage(String luiString) {
        Segment segment = stringToMap(luiString);
        return mapHdHealthCoverage(segment.getSegmentMap());
    }

    private IDC_IdentificationCard idcIdentificationCard(String luiString) {
        Segment segment = stringToMap(luiString);
        return mapIdcIdentificationCard(segment.getSegmentMap());
    }

    private LX_ProviderInformation lxProviderInformation(String lxString) {
        Segment segment = stringToMap(lxString);
        return mapLxProviderInformation(segment.getSegmentMap());
    }

    private PLA_ProviderChangeReason plaProviderChangeReason(String lxString) {
        Segment segment = stringToMap(lxString);
        return mapPlaProviderChangeReason(segment.getSegmentMap());
    }

    private COB_CoordinationOfBenefits cobCoordinationOfBenefits(String lxString) {
        Segment segment = stringToMap(lxString);
        return mapCobCoordinationOfBenefits(segment.getSegmentMap());
    }

    private LS_AdditionalReportingCategories lsAdditionalReportingCategories(String lxString) {
        Segment segment = stringToMap(lxString);
        return mapLsAdditionalReportingCategories(segment.getSegmentMap());
    }

    private LE_AdditionalReportingCategoriesTermination leAdditionalReportingCategoriesTermination(String lxString) {
        Segment segment = stringToMap(lxString);
        return mapLeAdditionalReportingCategoriesTermination(segment.getSegmentMap());
    }

    private LX_MemberReportingCategories lxMemberReportingCategories(String lxString) {
        Segment segment = stringToMap(lxString);
        return mapLxMemberReportingCategories(segment.getSegmentMap());
    }

    private LinkedList<String> gsSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        String[] split = ediData.split("#-#-#GS");
        for (String value : split) {
            if (value.startsWith("#-#-#ISA")) {
                stringLinkedList.add(value);
            } else {
                String gsAdded = "#-#-#GS" + value;
                stringLinkedList.add(gsAdded);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> iaeSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        String[] split = ediData.split("#-#-#IEA");
        for (String value : split) {
            if (value.startsWith("#-#-#GS")) {
                stringLinkedList.add(value);
            } else {
                String iaeAdded = "#-#-#IEA" + value;
                stringLinkedList.add(iaeAdded);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> stSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        LinkedList<String> geSplitList;
        String[] split = ediData.split("#-#-#ST");
        for (String value : split) {
            if (value.startsWith("#-#-#GS")) {
                stringLinkedList.add(value);
            } else {
                String stAdded = "#-#-#ST" + value;
                if (stAdded.contains("#-#-#GE")) {
                    geSplitList = geSplit(stAdded);
                    stringLinkedList.addAll(geSplitList);
                } else {
                    stringLinkedList.add(stAdded);
                }
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> geSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        String[] split = ediData.split("#-#-#GE");
        for (String value : split) {
            if (value.startsWith("#-#-#ST")) {
                stringLinkedList.add(value);
            } else {
                String geAdded = "#-#-#GE" + value;
                stringLinkedList.add(geAdded);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> seSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        LinkedList<String> insSplitList;
        String[] split = ediData.split("#-#-#SE");
        for (String value : split) {
            if (value.startsWith("#-#-#ST")) {
                if (value.contains("#-#-#INS")) {
                    insSplitList = insSplit(value);
                    stringLinkedList.addAll(insSplitList);
                }
            } else {
                String seAdded = "#-#-#SE" + value;
                stringLinkedList.add(seAdded);
            }
        }
        return stringLinkedList;
    }


    private LinkedList<String> bgnSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        LinkedList<String> n1SplitList;
        String[] split = ediData.split("#-#-#BGN");
        for (String value : split) {
            if (value.startsWith("#-#-#ST")) {
                stringLinkedList.add(value);
            } else {
                String bgnAdded = "#-#-#BGN" + value;
                n1SplitList = n1Split(bgnAdded);
                stringLinkedList.addAll(n1SplitList);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> n1Split(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        LinkedList<String> bgnSplitList;
        String[] split = ediData.split("#-#-#N1");
        for (String value : split) {
            if (value.startsWith("#-#-#BGN")) {
                bgnSplitList = tiltSplit(value);
                stringLinkedList.addAll(bgnSplitList);
            } else {
                String n1Added = "#-#-#N1" + value;
                stringLinkedList.add(n1Added);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> actSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        String[] split = ediData.split("#-#-#ACT");
        for (String value : split) {
            if (value.startsWith("#-#-#N1")) {
                stringLinkedList.add(value);
            } else {
                String actAdded = "#-#-#ACT" + value;
                stringLinkedList.add(actAdded);
            }
        }
        return stringLinkedList;
    }

    private LinkedList<String> tiltSplit(String dataString) {
        String[] segments = dataString.split("~");
        LinkedList<String> stringLinkedList = new LinkedList<>();
        for (String value : segments) {
            stringLinkedList.add(value + "~");
        }
        return stringLinkedList;
    }

    private LinkedList<String> insSplit(String ediData) {
        LinkedList<String> stringLinkedList = new LinkedList<>();
        LinkedList<String> bgnSplitList;
        String[] split = ediData.split("#-#-#INS");
        for (String value : split) {
            if (value.startsWith("#-#-#ST")) {
                if (value.contains("#-#-#BGN")) {
                    bgnSplitList = bgnSplit(value);
                    stringLinkedList.addAll(bgnSplitList);
                }
            } else {
                String insAdded = "#-#-#INS" + value;
                stringLinkedList.add(insAdded);
            }
        }
        return stringLinkedList;
    }

    private Segment stringToMap(String segmentString) {
        String[] segments = segmentString.split("~");
        Segment segmentValue = null;
        for (String segment : segments) {
            String[] elements = segment.split("\\*");
            segmentValue = new Segment();
            LinkedHashMap<String, String> valueMap = new LinkedHashMap<>();
            for (int i = 1; i < elements.length; i++) {
                valueMap.put("key" + i, elements[i]);
            }
            segmentValue.setSegmentName(elements[0]);
            segmentValue.setSegmentMap(valueMap);
        }
        return segmentValue;
    }

    private ISA mapISA(LinkedHashMap<String, String> isa) {
        return ISA.builder()
                .AuthorizationInformationQualifier_01(isa.get("key1"))
                .AuthorizationInformation_02(isa.get("key2"))
                .SecurityInformationQualifier_03(isa.get("key3"))
                .SecurityInformation_04(isa.get("key4"))
                .SenderIDQualifier_05(isa.get("key5"))
                .InterchangeSenderID_06(isa.get("key6"))
                .ReceiverIDQualifier_07(isa.get("key7"))
                .InterchangeReceiverID_08(isa.get("key8"))
                .InterchangeDate_09(isa.get("key9"))
                .InterchangeTime_10(isa.get("key10"))
                .InterchangeControlStandardsIdentifier_11(isa.get("key11"))
                .InterchangeControlVersionNumber_12(isa.get("key12"))
                .InterchangeControlNumber_13(isa.get("key13"))
                .AcknowledgementRequested_14(isa.get("key14"))
                .UsageIndicator_15(isa.get("key15"))
                .ComponentElementSeparator_16(isa.get("key16"))
                .build();
    }

    private GS mapGS(LinkedHashMap<String, String> gs) {
        return GS.builder()
                .CodeIdentifyingInformationType_01(gs.get("key1"))
                .SenderIDCode_02(gs.get("key2"))
                .ReceiverIDCode_03(gs.get("key"))
                .Date_04(gs.get("key4"))
                .Time_05(gs.get("key5"))
                .GroupControlNumber_06(gs.get("key6"))
                .TransactionTypeCode_07(gs.get("key7"))
                .VersionAndRelease_08(gs.get("key8")).build();
    }

    private ST mapST(LinkedHashMap<String, String> st) {
        return ST.builder()
                .TransactionSetIdentifierCode_01(st.get("key1"))
                .TransactionSetControlNumber_02(st.get("key2"))
                .ImplementationConventionPreference_03(st.get("key3")).build();
    }

    private GETrailers mapGETrailers(LinkedHashMap<String, String> geTrailers) {
        return GETrailers.builder()
                .NumberOfIncludedSets_01(geTrailers.get("key1"))
                .GroupControlNumber_02(geTrailers.get("key2"))
                .build();
    }

    private IEATrailers mapIEA(LinkedHashMap<String, String> iea) {
        return IEATrailers.builder()
                .NumberOfIncludedGroups_01(iea.get("key1"))
                .InterchangeControlNumber_02(iea.get("key2"))
                .build();
    }

    private SE mapSE(LinkedHashMap<String, String> se) {
        return SE.builder()
                .NumberOfIncludedSegments_01(se.get("key1"))
                .TransactionSetControlNumber_02(se.get("key2"))
                .build();
    }

    private BGN_BeginningSegment mapBGN(LinkedHashMap<String, String> bgn) {
        return BGN_BeginningSegment.builder()
                .TransactionSetPurposeCode_01(bgn.get("key1"))
                .TransactionSetReferenceNumber_02(bgn.get("key2"))
                .TransactionSetCreationDate_03(bgn.get("key3"))
                .TransactionSetCreationTime_04(bgn.get("key4"))
                .TimeZoneCode_05(bgn.get("key5"))
                .OriginalTransactionSetReferenceNumber_06(bgn.get("key6"))
                .TransactionTypeCode_07(bgn.get("key7"))
                .ActionCode_08(bgn.get("key8"))
                .SecurityLevelCode_09(bgn.get("key9"))
                .build();
    }

    private REF_TransactionSetPolicyNumber mapRefTransactionSetPolicyNumber(LinkedHashMap<String, String> ref) {
        return REF_TransactionSetPolicyNumber.builder()
                .ReferenceIdentificationQualifier_01(ref.get("key1"))
                .MemberGroupOrPolicyNumber_02(ref.get("key2"))
                .Description_03(ref.get("key3"))
                .ReferenceIdentifier_04(mapReferenceIdentifier04(ref.get("key4")))
                .build();
    }

    private REF_SubscriberIdentifier mapREFSubscriberIdentifier(LinkedHashMap<String, String> ref) {
        return REF_SubscriberIdentifier.builder()
                .ReferenceIdentificationQualifier_01(ref.get("key1"))
                .MemberGroupOrPolicyNumber_02(ref.get("key2"))
                .Description_03(ref.get("key3"))
                .ReferenceIdentifier_04(mapReferenceIdentifier04(ref.get("key4")))
                .build();
    }

    private REF_MemberPolicyNumber mapREFMemberPolicyNumber(LinkedHashMap<String, String> ref) {
        return REF_MemberPolicyNumber.builder()
                .ReferenceIdentificationQualifier_01(ref.get("key1"))
                .MemberGroupOrPolicyNumber_02(ref.get("key2"))
                .Description_03(ref.get("key3"))
                .ReferenceIdentifier_04(mapReferenceIdentifier04(ref.get("key4")))
                .build();
    }

    private REF_MemberSupplementalIdentifier mapREFMemberSupplementalIdentifier(LinkedHashMap<String, String> ref) {
        return REF_MemberSupplementalIdentifier.builder()
                .ReferenceIdentificationQualifier_01(ref.get("key1"))
                .MemberGroupOrPolicyNumber_02(ref.get("key2"))
                .Description_03(ref.get("key3"))
                .ReferenceIdentifier_04(mapReferenceIdentifier04(ref.get("key4")))
                .build();
    }

    private REF_HealthCoveragePolicyNumber mapRefHealthCoveragePolicyNumber(LinkedHashMap<String, String> ref) {
        return REF_HealthCoveragePolicyNumber.builder()
                .ReferenceIdentificationQualifier_01(ref.get("key1"))
                .MemberGroupOrPolicyNumber_02(ref.get("key2"))
                .Description_03(ref.get("key3"))
                .ReferenceIdentifier_04(mapReferenceIdentifier04(ref.get("key4")))
                .build();
    }

    private REF_AdditionalCoordinationOfBenefitsIdentifiers mapRefAdditionalCoordinationOfBenefitsIdentifiers(LinkedHashMap<String, String> ref) {
        return REF_AdditionalCoordinationOfBenefitsIdentifiers.builder()
                .ReferenceIdentificationQualifier_01(ref.get("key1"))
                .MemberGroupOrPolicyNumber_02(ref.get("key2"))
                .Description_03(ref.get("key3"))
                .ReferenceIdentifier_04(mapReferenceIdentifier04(ref.get("key4")))
                .build();
    }

    private REF_ReportingCategoryReference mapRefReportingCategoryReference(LinkedHashMap<String, String> ref) {
        return REF_ReportingCategoryReference.builder()
                .ReferenceIdentificationQualifier_01(ref.get("key1"))
                .MemberGroupOrPolicyNumber_02(ref.get("key2"))
                .Description_03(ref.get("key3"))
                .ReferenceIdentifier_04(mapReferenceIdentifier04(ref.get("key4")))
                .build();
    }

    private ReferenceIdentifier_04 mapReferenceIdentifier04(String segmentString) {
        ReferenceIdentifier_04 referenceIdentifier04 = new ReferenceIdentifier_04();
        if (segmentString != null && !segmentString.isEmpty()) {
            if (segmentString.contains(":")) {
                String[] segments = segmentString.split(":");
                referenceIdentifier04.setReferenceIdentificationQualifier_01(segments[0]);
                referenceIdentifier04.setReferenceIdentification_02(segments[1]);
                referenceIdentifier04.setReferenceIdentificationQualifier_03(segments[2]);
                referenceIdentifier04.setReferenceIdentification_04(segments[3]);
                referenceIdentifier04.setReferenceIdentificationQualifier_05(segments[4]);
                referenceIdentifier04.setReferenceIdentification_06(segments[5]);
            } else {
                referenceIdentifier04.setReferenceIdentificationQualifier_01(segmentString);
            }
        }
        return referenceIdentifier04;
    }

    private DTP_FileEffectiveDate mapDTPFileEffectiveDate(LinkedHashMap<String, String> dtp) {
        return DTP_FileEffectiveDate.builder()
                .DateTimeQualifier_01(dtp.get("key1"))
                .DateTimePeriodFormatQualifier_02(dtp.get("key2"))
                .DateTimePeriod_03(dtp.get("key3")).build();
    }

    private DTP_ReportingCategoryDate mapDtpReportingCategoryDate(LinkedHashMap<String, String> dtp) {
        return DTP_ReportingCategoryDate.builder()
                .DateTimeQualifier_01(dtp.get("key1"))
                .DateTimePeriodFormatQualifier_02(dtp.get("key2"))
                .DateTimePeriod_03(dtp.get("key3")).build();
    }

    private QTY_TransactionSetControlTotals mapQtyTransactionSetControlTotals(LinkedHashMap<String, String> qty) {
        return QTY_TransactionSetControlTotals.builder()
                .QuantityQualifier_01(qty.get("key1"))
                .AmbulancePatientCount_02(qty.get("key2"))
                .CompositeUnitOfMeasure_03(mapCompositeUnitOfMeasure03(qty.get("key3")))
                .FreeformInformation_04(qty.get("key4")).build();
    }

    private CompositeUnitOfMeasure_03 mapCompositeUnitOfMeasure03(String segmentString) {
        CompositeUnitOfMeasure_03 compositeUnitOfMeasure = new CompositeUnitOfMeasure_03();
        if (segmentString != null && !segmentString.isEmpty()) {
            if (segmentString.contains(":")) {
                String[] segments = segmentString.split(":");
                compositeUnitOfMeasure.setUnitOrBasisForMeasurementCode_01(segments[0]);
                compositeUnitOfMeasure.setExponent_02(segments[1]);
                compositeUnitOfMeasure.setMultiplier_03(segments[2]);
                compositeUnitOfMeasure.setUnitOrBasisForMeasurementCode_04(segments[3]);
                compositeUnitOfMeasure.setExponent_05(segments[4]);
                compositeUnitOfMeasure.setMultiplier_06(segments[5]);
                compositeUnitOfMeasure.setUnitOrBasisForMeasurementCode_07(segments[6]);
                compositeUnitOfMeasure.setExponent_08(segments[7]);
                compositeUnitOfMeasure.setMultiplier_09(segments[8]);
                compositeUnitOfMeasure.setUnitOrBasisForMeasurementCode_10(segments[9]);
                compositeUnitOfMeasure.setExponent_11(segments[10]);
                compositeUnitOfMeasure.setMultiplier_12(segments[11]);
                compositeUnitOfMeasure.setUnitOrBasisForMeasurementCode_13(segments[12]);
                compositeUnitOfMeasure.setExponent_14(segments[13]);
                compositeUnitOfMeasure.setMultiplier_15(segments[14]);
            } else {
                compositeUnitOfMeasure.setUnitOrBasisForMeasurementCode_01(segmentString);
            }
        }
        return compositeUnitOfMeasure;
    }

    private N1_SponsorName mapN1SponsorName(LinkedHashMap<String, String> n1) {
        return N1_SponsorName.builder()
                .EntityIdentifierCode_01(n1.get("key1"))
                .PremiumPayerName_02(n1.get("key2"))
                .IdentificationCodeQualifier_03(n1.get("key3"))
                .IntermediaryBankIdentifier_04(n1.get("key4"))
                .EntityRelationshipCode_05(n1.get("key5"))
                .EntityIdentifierCode_06(n1.get("key6")).build();
    }

    private N1_Payer mapN1Payer(LinkedHashMap<String, String> n1) {
        return N1_Payer.builder()
                .EntityIdentifierCode_01(n1.get("key1"))
                .PremiumPayerName_02(n1.get("key2"))
                .IdentificationCodeQualifier_03(n1.get("key3"))
                .IntermediaryBankIdentifier_04(n1.get("key4"))
                .EntityRelationshipCode_05(n1.get("key5"))
                .EntityIdentifierCode_06(n1.get("key6")).build();
    }

    private N1_TPA_BrokerName mapN1TpaBrokerName(LinkedHashMap<String, String> n1) {
        return N1_TPA_BrokerName.builder()
                .EntityIdentifierCode_01(n1.get("key1"))
                .PremiumPayerName_02(n1.get("key2"))
                .IdentificationCodeQualifier_03(n1.get("key3"))
                .IntermediaryBankIdentifier_04(n1.get("key4"))
                .EntityRelationshipCode_05(n1.get("key5"))
                .EntityIdentifierCode_06(n1.get("key6")).build();
    }

    private N1_ReportingCategory mapN1ReportingCategory(LinkedHashMap<String, String> n1) {
        return N1_ReportingCategory.builder()
                .EntityIdentifierCode_01(n1.get("key1"))
                .PremiumPayerName_02(n1.get("key2"))
                .IdentificationCodeQualifier_03(n1.get("key3"))
                .IntermediaryBankIdentifier_04(n1.get("key4"))
                .EntityRelationshipCode_05(n1.get("key5"))
                .EntityIdentifierCode_06(n1.get("key6")).build();
    }

    private ACT_TPA_BrokerAccountInformation mapActTpaBrokerAccountInformation(LinkedHashMap<String, String> act) {
        return ACT_TPA_BrokerAccountInformation.builder()
                .TPAorBrokerAccountNumber_01(act.get("key1"))
                .Name_02(act.get("key2"))
                .IdentificationCodeQualifier_03(act.get("key3"))
                .IdentificationCode_04(act.get("key4"))
                .AccountNumberQualifier_05(act.get("key5"))
                .TPAorBrokerAccountNumber_06(act.get("key6"))
                .Description_07(act.get("key7"))
                .PaymentMethodTypeCode_08(act.get("key8"))
                .BenefitStatusCode_09(act.get("key9")).build();
    }

    private INS_MemberLevelDetail mapInsMemberLevelDetail(LinkedHashMap<String, String> ins) {
        return INS_MemberLevelDetail.builder()
                .InsuredIndicator_01(ins.get("key1"))
                .IndividualRelationshipCode_02(ins.get("key2"))
                .MaintenanceTypeCode_03(ins.get("key3"))
                .MaintenanceReasonCode_04(ins.get("key4"))
                .BenefitStatusCode_05(ins.get("key5"))
                .MedicareStatusCode_06(mapMedicareStatusCode_06(ins.get("key6")))
                .ConsolidatedOmnibusBudgetReconciliationActCOBRAQualifyingEventCode_07(ins.get("key7"))
                .EmploymentStatusCode_08(ins.get("key8"))
                .StudentStatusCode_09(ins.get("key9"))
                .YesNoConditionOrResponseCode_10(ins.get("key10"))
                .DateTimePeriodFormatQualifier_11(ins.get("key11"))
                .DateTimePeriod_12(ins.get("key12"))
                .ConfidentialityCode_13(ins.get("key13"))
                .CityName_14(ins.get("key14"))
                .StateOrProvinceCode_15(ins.get("key15"))
                .CountryCode_16(ins.get("key16"))
                .BirthSequenceNumber_17(ins.get("key17"))
                .build();
    }

    private MedicareStatusCode_06 mapMedicareStatusCode_06(String segmentString) {
        MedicareStatusCode_06 medicareStatusCode06 = new MedicareStatusCode_06();
        if (segmentString != null && !segmentString.isEmpty()) {
            if (segmentString.contains(":")) {
                String[] segments = segmentString.split(":");
                medicareStatusCode06.setMedicarePlanCode_01(segments[0]);
                medicareStatusCode06.setEligibilityReasonCode_02(segments[1]);
                medicareStatusCode06.setEligibilityReasonCode_03(segments[2]);
                medicareStatusCode06.setEligibilityReasonCode_04(segments[3]);
            } else {
                medicareStatusCode06.setMedicarePlanCode_01(segmentString);
            }
        }
        return medicareStatusCode06;
    }

    private DTP_MemberLevelDates mapDTPMemberLevelDates(LinkedHashMap<String, String> dtp) {
        return DTP_MemberLevelDates.builder()
                .DateTimeQualifier_01(dtp.get("key1"))
                .DateTimePeriodFormatQualifier_02(dtp.get("key2"))
                .DateTimePeriod_03(dtp.get("key3")).build();
    }

    private DTP_CoordinationOfBenefitsEligibilityDates mapDtpCoordinationOfBenefitsEligibilityDates(LinkedHashMap<String, String> dtp) {
        return DTP_CoordinationOfBenefitsEligibilityDates.builder()
                .DateTimeQualifier_01(dtp.get("key1"))
                .DateTimePeriodFormatQualifier_02(dtp.get("key2"))
                .DateTimePeriod_03(dtp.get("key3")).build();
    }

    private NM1_MemberName mapNm1MemberName(LinkedHashMap<String, String> nm1) {
        return NM1_MemberName.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_IncorrectMemberName mapNm1IncorrectMemberName(LinkedHashMap<String, String> nm1) {
        return NM1_IncorrectMemberName.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_MemberMailingAddress mapNm1MemberMailingAddress(LinkedHashMap<String, String> nm1) {
        return NM1_MemberMailingAddress.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_MemberEmployer mapNm1MemberEmployer(LinkedHashMap<String, String> nm1) {
        return NM1_MemberEmployer.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_MemberSchool mapNm1MemberSchool(LinkedHashMap<String, String> nm1) {
        return NM1_MemberSchool.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_CustodialParent mapNm1CustodialParent(LinkedHashMap<String, String> nm1) {
        return NM1_CustodialParent.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_DropOffLocation mapNm1DropOffLocation(LinkedHashMap<String, String> nm1) {
        return NM1_DropOffLocation.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_ResponsiblePerson mapNm1ResponsiblePerson(LinkedHashMap<String, String> nm1) {
        return NM1_ResponsiblePerson.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_ProviderName mapNm1ProviderName(LinkedHashMap<String, String> nm1) {
        return NM1_ProviderName.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private NM1_CoordinationOfBenefitsRelatedEntity mapNm1CoordinationOfBenefitsRelatedEntity(LinkedHashMap<String, String> nm1) {
        return NM1_CoordinationOfBenefitsRelatedEntity.builder()
                .EntityIdentifierCode_01(nm1.get("key1"))
                .EntityTypeQualifier_02(nm1.get("key2"))
                .ResponseContactLastOrOrganizationName_03(nm1.get("key3"))
                .ResponseContactFirstName_04(nm1.get("key4"))
                .ResponseContactMiddleName_05(nm1.get("key5"))
                .NamePrefix_06(nm1.get("key6"))
                .ResponseContactNameSuffix_07(nm1.get("key7"))
                .IdentificationCodeQualifier_08(nm1.get("key8"))
                .ResponseContactIdentifier_09(nm1.get("key9"))
                .EntityRelationshipCode_10(nm1.get("key10"))
                .EntityIdentifierCode_11(nm1.get("key11"))
                .NameLastOrOrganizationName_12(nm1.get("key12"))
                .build();
    }

    private PER_MemberCommunicationsNumbers mapPerMemberCommunicationsNumbers(LinkedHashMap<String, String> per) {
        return PER_MemberCommunicationsNumbers.builder()
                .ContactFunctionCode_01(per.get("key1"))
                .ResponseContactName_02(per.get("key2"))
                .CommunicationNumberQualifier_03(per.get("key3"))
                .ResponseContactCommunicationNumber_04(per.get("key4"))
                .CommunicationNumberQualifier_05(per.get("key5"))
                .ResponseContactCommunicationNumber_06(per.get("key6"))
                .CommunicationNumberQualifier_07(per.get("key7"))
                .ResponseContactCommunicationNumber_08(per.get("key8"))
                .ContactInquiryReference_09(per.get("key9"))
                .build();
    }

    private PER_MemberEmployerCommunicationsNumbers mapPerMemberEmployerCommunicationsNumbers(LinkedHashMap<String, String> per) {
        return PER_MemberEmployerCommunicationsNumbers.builder()
                .ContactFunctionCode_01(per.get("key1"))
                .ResponseContactName_02(per.get("key2"))
                .CommunicationNumberQualifier_03(per.get("key3"))
                .ResponseContactCommunicationNumber_04(per.get("key4"))
                .CommunicationNumberQualifier_05(per.get("key5"))
                .ResponseContactCommunicationNumber_06(per.get("key6"))
                .CommunicationNumberQualifier_07(per.get("key7"))
                .ResponseContactCommunicationNumber_08(per.get("key8"))
                .ContactInquiryReference_09(per.get("key9"))
                .build();
    }

    private PER_MemberSchoolCommunicationsNumbers mapPerMemberSchoolCommunicationsNumbers(LinkedHashMap<String, String> per) {
        return PER_MemberSchoolCommunicationsNumbers.builder()
                .ContactFunctionCode_01(per.get("key1"))
                .ResponseContactName_02(per.get("key2"))
                .CommunicationNumberQualifier_03(per.get("key3"))
                .ResponseContactCommunicationNumber_04(per.get("key4"))
                .CommunicationNumberQualifier_05(per.get("key5"))
                .ResponseContactCommunicationNumber_06(per.get("key6"))
                .CommunicationNumberQualifier_07(per.get("key7"))
                .ResponseContactCommunicationNumber_08(per.get("key8"))
                .ContactInquiryReference_09(per.get("key9"))
                .build();
    }

    private PER_CustodialParentCommunicationsNumbers mapPerCustodialParentCommunicationsNumbers(LinkedHashMap<String, String> per) {
        return PER_CustodialParentCommunicationsNumbers.builder()
                .ContactFunctionCode_01(per.get("key1"))
                .ResponseContactName_02(per.get("key2"))
                .CommunicationNumberQualifier_03(per.get("key3"))
                .ResponseContactCommunicationNumber_04(per.get("key4"))
                .CommunicationNumberQualifier_05(per.get("key5"))
                .ResponseContactCommunicationNumber_06(per.get("key6"))
                .CommunicationNumberQualifier_07(per.get("key7"))
                .ResponseContactCommunicationNumber_08(per.get("key8"))
                .ContactInquiryReference_09(per.get("key9"))
                .build();
    }

    private PER_ResponsiblePersonCommunicationsNumbers mapPerResponsiblePersonCommunicationsNumbers(LinkedHashMap<String, String> per) {
        return PER_ResponsiblePersonCommunicationsNumbers.builder()
                .ContactFunctionCode_01(per.get("key1"))
                .ResponseContactName_02(per.get("key2"))
                .CommunicationNumberQualifier_03(per.get("key3"))
                .ResponseContactCommunicationNumber_04(per.get("key4"))
                .CommunicationNumberQualifier_05(per.get("key5"))
                .ResponseContactCommunicationNumber_06(per.get("key6"))
                .CommunicationNumberQualifier_07(per.get("key7"))
                .ResponseContactCommunicationNumber_08(per.get("key8"))
                .ContactInquiryReference_09(per.get("key9"))
                .build();
    }

    private PER_ProviderCommunicationsNumbers mapPerProviderCommunicationsNumbers(LinkedHashMap<String, String> per) {
        return PER_ProviderCommunicationsNumbers.builder()
                .ContactFunctionCode_01(per.get("key1"))
                .ResponseContactName_02(per.get("key2"))
                .CommunicationNumberQualifier_03(per.get("key3"))
                .ResponseContactCommunicationNumber_04(per.get("key4"))
                .CommunicationNumberQualifier_05(per.get("key5"))
                .ResponseContactCommunicationNumber_06(per.get("key6"))
                .CommunicationNumberQualifier_07(per.get("key7"))
                .ResponseContactCommunicationNumber_08(per.get("key8"))
                .ContactInquiryReference_09(per.get("key9"))
                .build();
    }

    private PER_AdministrativeCommunicationsContact mapPerAdministrativeCommunicationsContact(LinkedHashMap<String, String> per) {
        return PER_AdministrativeCommunicationsContact.builder()
                .ContactFunctionCode_01(per.get("key1"))
                .ResponseContactName_02(per.get("key2"))
                .CommunicationNumberQualifier_03(per.get("key3"))
                .ResponseContactCommunicationNumber_04(per.get("key4"))
                .CommunicationNumberQualifier_05(per.get("key5"))
                .ResponseContactCommunicationNumber_06(per.get("key6"))
                .CommunicationNumberQualifier_07(per.get("key7"))
                .ResponseContactCommunicationNumber_08(per.get("key8"))
                .ContactInquiryReference_09(per.get("key9"))
                .build();
    }

    private N3_MemberResidenceStreetAddress mapN3MemberResidenceStreetAddress(LinkedHashMap<String, String> n3) {
        return N3_MemberResidenceStreetAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N3_MemberMailStreetAddress mapN3MemberMailStreetAddress(LinkedHashMap<String, String> n3) {
        return N3_MemberMailStreetAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N3_MemberEmployerStreetAddress mapN3MemberEmployerStreetAddress(LinkedHashMap<String, String> n3) {
        return N3_MemberEmployerStreetAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N3_MemberSchoolStreetAddress mapN3MemberSchoolStreetAddress(LinkedHashMap<String, String> n3) {
        return N3_MemberSchoolStreetAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N3_CustodialParentStreetAddress mapN3CustodialParentStreetAddress(LinkedHashMap<String, String> n3) {
        return N3_CustodialParentStreetAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N3_ResponsiblePersonStreetAddress mapN3ResponsiblePersonStreetAddress(LinkedHashMap<String, String> n3) {
        return N3_ResponsiblePersonStreetAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N3_DropOffLocationStreetAddress mapN3DropOffLocationStreetAddress(LinkedHashMap<String, String> n3) {
        return N3_DropOffLocationStreetAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N3_ProviderAddress mapN3ProviderAddress(LinkedHashMap<String, String> n3) {
        return N3_ProviderAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N3_CoordinationOfBenefitsRelatedEntityAddress mapN3CoordinationOfBenefitsRelatedEntityAddress(LinkedHashMap<String, String> n3) {
        return N3_CoordinationOfBenefitsRelatedEntityAddress.builder()
                .ResponseContactAddressLine_01(n3.get("key1"))
                .ResponseContactAddressLine_02(n3.get("key2"))
                .build();
    }

    private N4_MemberCity_State_ZIPCode mapN4MemberCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_MemberCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private N4_MemberMailCity_State_ZIPCode mapN4MemberMailCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_MemberMailCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private N4_MemberEmployerCity_State_ZIPCode mapN4MemberEmployerCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_MemberEmployerCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private N4_MemberSchoolCity_State_ZIPCode mapN4MemberSchoolCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_MemberSchoolCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private N4_CustodialParentCity_State_ZIPCode mapN4CustodialParentCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_CustodialParentCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private N4_ResponsiblePersonCity_State_ZIPCode mapN4ResponsiblePersonCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_ResponsiblePersonCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private N4_DropOffLocationCity_State_ZIPCode mapN4DropOffLocationCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_DropOffLocationCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private N4_ProviderCity_State_ZIPCode mapN4ProviderCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_ProviderCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private N4_CoordinationOfBenefitsOtherInsuranceCompanyCity_State_ZIPCode mapN4CoordinationOfBenefitsOtherInsuranceCompanyCityStateZipCode(LinkedHashMap<String, String> n4) {
        return N4_CoordinationOfBenefitsOtherInsuranceCompanyCity_State_ZIPCode.builder()
                .AdditionalPatientInformationContactCityName_01(n4.get("key1"))
                .AdditionalPatientInformationContactStateCode_02(n4.get("key2"))
                .AdditionalPatientInformationContactPostalZoneOrZIPCode_03(n4.get("key3"))
                .CountryCode_04(n4.get("key4"))
                .LocationQualifier_05(n4.get("key5"))
                .LocationIdentifier_06(n4.get("key6"))
                .CountrySubdivisionCode_07(n4.get("key7"))
                .build();
    }

    private DMG_MemberDemographics mapDmgMemberDemographics(LinkedHashMap<String, String> dmg) {
        return DMG_MemberDemographics.builder()
                .DateTimePeriodFormatQualifier_01(dmg.get("key1"))
                .DependentBirthDate_02(dmg.get("key2"))
                .DependentGenderCode_03(dmg.get("key3"))
                .MaritalStatusCode_04(dmg.get("key4"))
                .compositeRaceOrEthnicityInformation05(List.of(mapCompositeRaceOrEthnicityInformation05(dmg.get("key5"))))
                .CitizenshipStatusCode_06(dmg.get("key6"))
                .CountryCode_07(dmg.get("key7"))
                .BasisOfVerificationCode_08(dmg.get("key8"))
                .Quantity_09(dmg.get("key9"))
                .CodeListQualifierCode_10(dmg.get("key10"))
                .IndustryCode_11(dmg.get("key11"))
                .build();
    }

    private DMG_IncorrectMemberDemographics mapDmgIncorrectMemberDemographics(LinkedHashMap<String, String> dmg) {
        return DMG_IncorrectMemberDemographics.builder()
                .DateTimePeriodFormatQualifier_01(dmg.get("key1"))
                .DependentBirthDate_02(dmg.get("key2"))
                .DependentGenderCode_03(dmg.get("key3"))
                .MaritalStatusCode_04(dmg.get("key4"))
                .compositeRaceOrEthnicityInformation05(List.of(mapCompositeRaceOrEthnicityInformation05(dmg.get("key5"))))
                .CitizenshipStatusCode_06(dmg.get("key6"))
                .CountryCode_07(dmg.get("key7"))
                .BasisOfVerificationCode_08(dmg.get("key8"))
                .Quantity_09(dmg.get("key9"))
                .CodeListQualifierCode_10(dmg.get("key10"))
                .IndustryCode_11(dmg.get("key11"))
                .build();
    }

    private CompositeRaceOrEthnicityInformation_05 mapCompositeRaceOrEthnicityInformation05(String segmentString) {
        CompositeRaceOrEthnicityInformation_05 compositeRaceOrEthnicityInformation05 = new CompositeRaceOrEthnicityInformation_05();
        if (segmentString != null && !segmentString.isEmpty()) {
            if (segmentString.contains(":")) {
                String[] segments = segmentString.split(":");
                compositeRaceOrEthnicityInformation05.setRaceOrEthnicityCode_01(segments[0]);
                compositeRaceOrEthnicityInformation05.setCodeListQualifierCode_02(segments[1]);
                compositeRaceOrEthnicityInformation05.setIndustryCode_03(segments[2]);
            } else {
                compositeRaceOrEthnicityInformation05.setRaceOrEthnicityCode_01(segmentString);
            }
        }
        return compositeRaceOrEthnicityInformation05;
    }

    private EC_EmploymentClass mapEcEmploymentClass(LinkedHashMap<String, String> ec) {
        return EC_EmploymentClass.builder()
                .EmploymentClassCode_01(ec.get("key1"))
                .EmploymentClassCode_02(ec.get("key2"))
                .EmploymentClassCode_03(ec.get("key3"))
                .PercentageAsDecimal_04(ec.get("key4"))
                .InformationStatusCode_05(ec.get("key5"))
                .OccupationCode_06(ec.get("key6"))
                .build();
    }

    private ICM_MemberIncome mapIcmMemberIncome(LinkedHashMap<String, String> icm) {
        return ICM_MemberIncome.builder()
                .FrequencyCode_01(icm.get("key1"))
                .WageAmount_02(icm.get("key2"))
                .WorkHoursCount_03(icm.get("key3"))
                .LocationIdentificationCode_04(icm.get("key4"))
                .SalaryGradeCode_05(icm.get("key5"))
                .CurrencyCode_06(icm.get("key6"))
                .build();
    }

    private AMT_MemberPolicyAmounts mapAmtMemberPolicyAmounts(LinkedHashMap<String, String> amt) {
        return AMT_MemberPolicyAmounts.builder()
                .AmountQualifierCode_01(amt.get("key1"))
                .TotalClaimChargeAmount_02(amt.get("key2"))
                .CreditDebitFlagCode_03(amt.get("key3"))
                .build();
    }

    private HLH_MemberHealthInformation mapHlhMemberHealthInformation(LinkedHashMap<String, String> hlh) {
        return HLH_MemberHealthInformation.builder()
                .HealthRelatedCode_01(hlh.get("key1"))
                .MemberHeight_02(hlh.get("key2"))
                .MemberWeight_03(hlh.get("key3"))
                .Weight_04(hlh.get("key4"))
                .Description_05(hlh.get("key5"))
                .CurrentHealthConditionCode_06(hlh.get("key6"))
                .Description_07(hlh.get("key7")).build();
    }

    private LUI_MemberLanguage mapLuiMemberLanguage(LinkedHashMap<String, String> lui) {
        return LUI_MemberLanguage.builder()
                .IdentificationCodeQualifier_01(lui.get("key1"))
                .LanguageCode_02(lui.get("key2"))
                .LanguageDescription_03(lui.get("key3"))
                .LanguageUseIndicator_04(lui.get("key4"))
                .LanguageProficiencyIndicator_05(lui.get("key5"))
                .build();
    }

    private DSB_DisabilityInformation mapDsbDisabilityInformation(LinkedHashMap<String, String> dsb) {
        return DSB_DisabilityInformation.builder()
                .DisabilityTypeCode_01(dsb.get("key1"))
                .Quantity_02(dsb.get("key2"))
                .OccupationCode_03(dsb.get("key3"))
                .WorkIntensityCode_04(dsb.get("key4"))
                .ProductOptionCode_05(dsb.get("key5"))
                .MonetaryAmount_06(dsb.get("key6"))
                .ProductOrServiceIDQualifier_07(dsb.get("key7"))
                .DiagnosisCode_08(dsb.get("key8"))
                .build();
    }

    private DTP_DisabilityEligibilityDates mapDtpDisabilityEligibilityDates(LinkedHashMap<String, String> dtp) {
        return DTP_DisabilityEligibilityDates.builder()
                .DateTimeQualifier_01(dtp.get("key1"))
                .DateTimePeriodFormatQualifier_02(dtp.get("key2"))
                .DateTimePeriod_03(dtp.get("key3")).build();
    }

    private HD_HealthCoverage mapHdHealthCoverage(LinkedHashMap<String, String> hd) {
        return HD_HealthCoverage.builder()
                .MaintenanceTypeCode_01(hd.get("key1"))
                .MaintenanceReasonCode_02(hd.get("key2"))
                .InsuranceLineCode_03(hd.get("key3"))
                .PlanCoverageDescription_04(hd.get("key4"))
                .CoverageLevelCode_05(hd.get("key5"))
                .Count_06(hd.get("key6"))
                .Count_07(hd.get("key7"))
                .UnderwritingDecisionCode_08(hd.get("key8"))
                .LateEnrollmentIndicator_09(hd.get("key9"))
                .DrugHouseCode_10(hd.get("key10"))
                .YesNoConditionOrResponseCode_11(hd.get("key11"))
                .build();
    }

    private DTP_HealthCoverageDates mapDtpHealthCoverageDates(LinkedHashMap<String, String> dtp) {
        return DTP_HealthCoverageDates.builder()
                .DateTimeQualifier_01(dtp.get("key1"))
                .DateTimePeriodFormatQualifier_02(dtp.get("key2"))
                .DateTimePeriod_03(dtp.get("key3")).build();
    }

    private AMT_HealthCoveragePolicy mapAmtHealthCoveragePolicy(LinkedHashMap<String, String> amt) {
        return AMT_HealthCoveragePolicy.builder()
                .AmountQualifierCode_01(amt.get("key1"))
                .TotalClaimChargeAmount_02(amt.get("key2"))
                .CreditDebitFlagCode_03(amt.get("key3")).build();
    }

    private IDC_IdentificationCard mapIdcIdentificationCard(LinkedHashMap<String, String> idc) {
        return IDC_IdentificationCard.builder()
                .PlanCoverageDescription_01(idc.get("key1"))
                .IdentificationCardTypeCode_02(idc.get("key2"))
                .IdentificationCardCount_03(idc.get("key3"))
                .ActionCode_04(idc.get("key4"))
                .build();
    }

    private LX_ProviderInformation mapLxProviderInformation(LinkedHashMap<String, String> lx) {
        return LX_ProviderInformation.builder()
                .AssignedNumber_01(lx.get("key1"))
                .build();
    }

    private PLA_ProviderChangeReason mapPlaProviderChangeReason(LinkedHashMap<String, String> pla) {
        return PLA_ProviderChangeReason.builder()
                .ActionCode_01(pla.get("key1"))
                .EntityIdentifierCode_02(pla.get("key2"))
                .ProviderEffectiveDate_03(pla.get("key3"))
                .Time_04(pla.get("key4"))
                .MaintenanceReasonCode_05(pla.get("key5"))
                .build();
    }

    private COB_CoordinationOfBenefits mapCobCoordinationOfBenefits(LinkedHashMap<String, String> cob) {
        return COB_CoordinationOfBenefits.builder()
                .PayerResponsibilitySequenceNumberCode_01(cob.get("key1"))
                .MemberGroupOrPolicyNumber_02(cob.get("key2"))
                .CoordinationOfBenefitsCode_03(cob.get("key3"))
                .ServiceTypeCode_04(mapServiceTypeCode(cob.get("key4")))
                .build();
    }

    private List<String> mapServiceTypeCode(String serviceTypeCodeString) {
        LinkedList<String> linkedList = new LinkedList<>();
        if (serviceTypeCodeString != null && !serviceTypeCodeString.isEmpty()) {
            if (serviceTypeCodeString.contains(":")) {
                String[] segments = serviceTypeCodeString.split(":");
                linkedList.addAll(Arrays.asList(segments));
            } else {
                linkedList.add(serviceTypeCodeString);
            }
        }
        return linkedList;
    }

    private LS_AdditionalReportingCategories mapLsAdditionalReportingCategories(LinkedHashMap<String, String> ls) {
        return LS_AdditionalReportingCategories.builder()
                .LoopIdentifierCode_01(ls.get("key1"))
                .build();
    }

    private LE_AdditionalReportingCategoriesTermination mapLeAdditionalReportingCategoriesTermination(LinkedHashMap<String, String> le) {
        return LE_AdditionalReportingCategoriesTermination.builder()
                .LoopIdentifierCode_01(le.get("key1"))
                .build();
    }

    private LX_MemberReportingCategories mapLxMemberReportingCategories(LinkedHashMap<String, String> lx) {
        return LX_MemberReportingCategories.builder()
                .AssignedNumber_01(lx.get("key1"))
                .build();
    }
}