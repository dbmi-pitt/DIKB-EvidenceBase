SELECT * FROM assertion1 assrt, evidenceTag ev 
where assrt.evidenceType = ev.abbreviation 
AND dateAnnotated <> '0000-00-00 00:00:00'
ORDER BY assrt.dateAnnotated DESC 

