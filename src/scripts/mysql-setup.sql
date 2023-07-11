-- cardholder database
DROP DATABASE IF EXISTS cardholder;
DROP USER IF EXISTS `cardholderadmin`@`%`;
DROP USER IF EXISTS `cardholderuser`@`%`;
CREATE DATABASE IF NOT EXISTS cardholder CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `cardholderadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `cardholder`.* TO `cardholderadmin`@`%`;
CREATE USER IF NOT EXISTS `cardholderuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `cardholder`.* TO `cardholderuser`@`%`;
FLUSH PRIVILEGES;

-- card database
DROP DATABASE IF EXISTS card;
DROP USER IF EXISTS `cardadmin`@`%`;
DROP USER IF EXISTS `carduser`@`%`;
CREATE DATABASE IF NOT EXISTS card CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `cardadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `card`.* TO `cardadmin`@`%`;
CREATE USER IF NOT EXISTS `carduser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `card`.* TO `carduser`@`%`;
FLUSH PRIVILEGES;