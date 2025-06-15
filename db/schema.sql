-- Create Database
CREATE DATABASE IF NOT EXISTS cms_db;
USE cms_db;

-- Create Table: users
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('Employee', 'Admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Table: complaints
CREATE TABLE complaints (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            user_id INT NOT NULL,
                            description TEXT NOT NULL,
                            status ENUM('Pending', 'In-Progress', 'Resolved') NOT NULL DEFAULT 'Pending',
                            remarks TEXT,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert Sample Data: users
INSERT INTO users (username, password, role) VALUES
                                                 ('employee1', 'pass123', 'Employee'),
                                                 ('employee2', 'pass123', 'Employee'),
                                                 ('admin1', 'admin123', 'Admin');

-- Insert Sample Data: complaints
INSERT INTO complaints (user_id, description, status, remarks) VALUES
                                                                   (1, 'Broken chair in office', 'Pending', NULL),
                                                                   (1, 'AC not working in meeting room', 'In-Progress', 'Technician assigned'),
                                                                   (2, 'Printer out of ink', 'Resolved', 'Ink refilled on 2025-06-10');