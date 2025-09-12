-- Migration script to handle schema differences between backup and current database
-- This script consolidates the feedback table schema

-- Use the feedback_db database
USE `feedback_db`;

-- Drop existing table if it exists (backup current data first if needed)
-- DROP TABLE IF EXISTS `feedback`;

-- Create unified feedback table with all required fields
CREATE TABLE IF NOT EXISTS `feedback_unified` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_name` VARCHAR(255) NOT NULL COMMENT 'Username from legacy schema',
    `first_name` VARCHAR(100) DEFAULT NULL COMMENT 'First name from current schema',
    `last_name` VARCHAR(100) DEFAULT NULL COMMENT 'Last name from current schema',
    `email` VARCHAR(255) NOT NULL COMMENT 'Email address',
    `feedback_text` TEXT NOT NULL COMMENT 'Feedback content - unified field name',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation timestamp',
    `submission_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Legacy field for compatibility'
);

-- Create indexes for better performance
CREATE INDEX `idx_email` ON `feedback_unified` (`email`);
CREATE INDEX `idx_created_at` ON `feedback_unified` (`created_at`);
CREATE INDEX `idx_user_name` ON `feedback_unified` (`user_name`);

-- Migration steps (uncomment when ready to migrate):
-- 1. Backup existing data
-- INSERT INTO feedback_unified (user_name, email, feedback_text, created_at)
-- SELECT user_name, 'unknown@example.com', feedback_text, submission_date FROM feedback;

-- 2. Rename tables
-- RENAME TABLE feedback TO feedback_old;
-- RENAME TABLE feedback_unified TO feedback;

-- Note: Update application code to match the unified schema