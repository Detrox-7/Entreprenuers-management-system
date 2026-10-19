CREATE DATABASE IF NOT EXISTS entrepreneurs_management_system CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE entrepreneurs_management_system;

CREATE TABLE IF NOT EXISTS users (
  user_id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(60) NOT NULL UNIQUE,
  password_hash VARCHAR(100) NOT NULL, role VARCHAR(30) NOT NULL DEFAULT 'ADMIN',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS entrepreneurs (
  entrepreneur_id INT PRIMARY KEY AUTO_INCREMENT, full_name VARCHAR(120) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE, phone VARCHAR(30), gender VARCHAR(30), date_of_birth DATE,
  address VARCHAR(255), city VARCHAR(80), state VARCHAR(80), registration_date DATE NOT NULL,
  status VARCHAR(30) NOT NULL DEFAULT 'Active', created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS businesses (
  business_id INT PRIMARY KEY AUTO_INCREMENT,
  entrepreneur_id INT NOT NULL, business_name VARCHAR(150) NOT NULL, business_type VARCHAR(60),
  industry VARCHAR(100), description TEXT, founded_date DATE, location VARCHAR(180),
  status VARCHAR(30) NOT NULL DEFAULT 'Active', created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_business_entrepreneur FOREIGN KEY (entrepreneur_id) REFERENCES entrepreneurs(entrepreneur_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS employees (
  employee_id INT PRIMARY KEY AUTO_INCREMENT, business_id INT NOT NULL, employee_name VARCHAR(120) NOT NULL,
  email VARCHAR(150), phone VARCHAR(30), designation VARCHAR(100), joining_date DATE,
  CONSTRAINT fk_employee_business FOREIGN KEY (business_id) REFERENCES businesses(business_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS investments (
  investment_id INT PRIMARY KEY AUTO_INCREMENT, business_id INT NOT NULL, investor_name VARCHAR(120) NOT NULL,
  investment_amount DECIMAL(14,2) NOT NULL, investment_date DATE, investment_type VARCHAR(60),
  CONSTRAINT fk_investment_business FOREIGN KEY (business_id) REFERENCES businesses(business_id) ON DELETE CASCADE
);
