INSERT INTO ndcodes(Code, Description) VALUES
('00904-2407','Tetracycline'),
('08109-6','Aspirin'),
('64764-1512','Prioglitazone'),
('54868-4985', 'Citalopram Hydrobromide'),
('00060-431', 'Benzoyl Peroxide'),
('0603-1585','Codeine'),
('13310-0145','Bactrim'),
(' 53746-464','Ibuprofen')

ON DUPLICATE KEY UPDATE Code = Code;
