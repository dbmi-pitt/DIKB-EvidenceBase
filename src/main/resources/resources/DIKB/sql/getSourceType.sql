
SELECT A.evidenceType,E.tag,COUNT(*) as num 
FROM DIKB A,evidenceTag E 
where A.evidenceType = E.abbreviation 
and A.researchStatementLabel like "%example1%" 
and A.researchStatementLabel like "%example2%" 
group by A.evidenceType