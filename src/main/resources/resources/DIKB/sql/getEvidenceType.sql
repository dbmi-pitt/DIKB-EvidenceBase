
SELECT assrt.evidenceType,evtag.tag,COUNT(*) as num 
FROM assertion1 assrt,evidenceTag evtag
where assrt.evidenceType = evtag.abbreviation 
and assrt.researchStatementLabel like '%example1%'
and assrt.researchStatementLabel like '%example2%' 
group by assrt.evidenceType, evtag.tag;
