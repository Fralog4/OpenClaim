-- Inserimento di PolicyHolder
INSERT INTO policy_holder (policy_holder_name, policy_holder_last_name, policy_holder_email, policy_holder_phone_number, policy_holder_address, plate_number)
VALUES ('Paolo', 'Bianchi', 'paolo.bianchi@example.com', '3331112222', 'Via Verdi 10, Milano', 'AB123CD');
INSERT INTO policy_holder (policy_holder_name, policy_holder_last_name, policy_holder_email, policy_holder_phone_number, policy_holder_address, plate_number)
VALUES ('Laura', 'Rossi', 'laura.rossi@example.com', '3333334444', 'Via Roma 20, Roma', 'XY456ZA');

-- Inserimento di Policy
INSERT INTO policy (policy_number, policy_holder_id) VALUES ('POL0001', 1);
INSERT INTO policy (policy_number, policy_holder_id) VALUES ('POL0002', 2);

-- Inserimento di Claim
INSERT INTO claim (policy_id, claim_status, claim_date) VALUES (1, 'OPEN', '2024-03-10');
INSERT INTO claim (policy_id, claim_status, claim_date) VALUES (2, 'CLOSED', '2024-02-20');

-- Inserimento di Claimant
INSERT INTO claimant (claimant_name, claimant_last_name, claimant_email, claimant_phone_number, claimant_address, plate_number, birth_date, gender, claim_id)
VALUES ('Marco', 'Neri', 'marco.neri@example.com', '3335556666', 'Via Napoli 30, Napoli', 'EF789GH', '1990-05-15', 'M', 1);
INSERT INTO claimant (claimant_name, claimant_last_name, claimant_email, claimant_phone_number, claimant_address, plate_number, birth_date, gender, claim_id)
VALUES ('Giulia', 'Gialli', 'giulia.gialli@example.com', '3337778888', 'Via Firenze 40, Firenze', 'IJ012KL', '1995-10-25', 'F', 2);

-- Inserimento di Defendant
INSERT INTO defendant (defendant_name, defendant_last_name, defendant_email, defendant_phone_number, defendant_address, plate_number, claim_id)
VALUES ('Luca', 'Verdi', 'luca.verdi@example.com', '3339990000', 'Via Torino 50, Torino', 'MN345OP', 1);
INSERT INTO defendant (defendant_name, defendant_last_name, defendant_email, defendant_phone_number, defendant_address, plate_number, claim_id)
VALUES ('Elena', 'Blu', 'elena.blu@example.com', '3332221111', 'Via Palermo 60, Palermo', 'QR678ST', 2);