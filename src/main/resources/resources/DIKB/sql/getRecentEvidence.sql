SELECT * FROM DIKB A, evidenceTag E 
where A.evidenceType = E.abbreviation 
AND dateAnnotated <> "0000-00-00 00:00:00" 
ORDER BY A.dateAnnotated DESC 

