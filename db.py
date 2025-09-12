import pymysql 
import logging 
import traceback 
import os

# Database configuration - now supporting environment variables
DB_HOST = os.getenv('DB_HOST', 'localhost')
DB_USER = os.getenv('DB_USER', 'root')
DB_PASSWORD = os.getenv('DB_PASSWORD', '')  # Set via environment variable for security
DB_NAME = os.getenv('DB_NAME', 'feedback_db')
DB_PORT = int(os.getenv('DB_PORT', '3307'))

def get_db_connection(): 
    try: 
        logging.info("Attempting to connect to the database.") 
        connection = pymysql.connect( 
            host=DB_HOST, 
            user=DB_USER, 
            password=DB_PASSWORD, 
            database=DB_NAME, 
            port=DB_PORT, 
            cursorclass=pymysql.cursors.DictCursor 
        ) 
        logging.info("Database connection established successfully.") 
        return connection 
    except Exception as e: 
        logging.error("Failed to connect to the database: %s", traceback.format_exc()) 
        return None

def get_db_config():
    """Returns current database configuration for debugging"""
    return {
        'host': DB_HOST,
        'user': DB_USER,
        'database': DB_NAME,
        'port': DB_PORT,
        'password_set': bool(DB_PASSWORD)
    }