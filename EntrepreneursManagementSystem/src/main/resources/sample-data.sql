USE entrepreneurs_management_system;

INSERT INTO entrepreneurs(full_name,email,phone,gender,date_of_birth,address,city,state,registration_date,status) VALUES
('Aditi Sharma','aditi.sharma@example.edu','+91 98765 43210','Female','2001-04-12','12 Model Colony','Pune','Maharashtra','2026-08-18','Active'),
('Rahul Mehta','rahul.mehta@example.edu','+91 98220 11223','Male','2000-11-03','8 Marine Lines','Mumbai','Maharashtra','2026-08-20','Active'),
('Neha Iyer','neha.iyer@example.edu','+91 98450 22334','Female','2002-02-25','40 Indiranagar','Bengaluru','Karnataka','2026-08-22','Pending'),
('Arjun Patel','arjun.patel@example.edu','+91 98980 33445','Male','2001-07-19','16 Navrangpura','Ahmedabad','Gujarat','2026-08-25','Active'),
('Priya Nair','priya.nair@example.edu','+91 97460 44556','Female','2002-01-09','21 Panampilly Nagar','Kochi','Kerala','2026-08-28','Pending');

INSERT INTO businesses(entrepreneur_id,business_name,business_type,industry,description,founded_date,location,status) VALUES
(1,'Green Basket','Startup','Agriculture','Farm-to-home organic produce platform.','2025-06-01','Pune, Maharashtra','Active'),
(2,'FinBridge Solutions','Small Business','Finance','Financial literacy and advisory for local businesses.','2024-03-15','Mumbai, Maharashtra','Active'),
(3,'HealthNest','Startup','Healthcare','Affordable appointment and care coordination service.','2026-01-20','Bengaluru, Karnataka','Pending'),
(4,'CraftLane','Startup','Retail','Online marketplace for independent craft makers.','2025-10-01','Ahmedabad, Gujarat','Active'),
(5,'LearnLoop','Startup','Education','Peer-led learning resources for college students.','2026-02-10','Kochi, Kerala','Pending');

INSERT INTO employees(business_id,employee_name,email,phone,designation,joining_date) VALUES
(1,'Kavya Rao','kavya.rao@example.edu','+91 98760 10001','Operations Associate','2025-07-01'),
(2,'Ishaan Kulkarni','ishaan.k@example.edu','+91 98760 10002','Business Analyst','2024-05-10'),
(4,'Sana Desai','sana.desai@example.edu','+91 98760 10003','Community Manager','2025-11-01');

INSERT INTO investments(business_id,investor_name,investment_amount,investment_date,investment_type) VALUES
(1,'Campus Innovation Fund',250000.00,'2025-09-15','Seed'),
(3,'Health Futures Network',500000.00,'2026-03-10','Grant'),
(5,'Alumni Startup Circle',150000.00,'2026-04-05','Angel');
