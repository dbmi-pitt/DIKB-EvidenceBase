
SELECT A.evidenceType,E.tag,COUNT(*) as num 
FROM assertion1 A,evidenceTag E 
where A.evidenceType = E.abbreviation 
and A.researchStatementLabel like "%example%" 
group by A.evidenceType