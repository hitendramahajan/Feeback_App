import pymysql 
import logging 
import traceback 
 
# Database configuration 
DB_HOST = 'localhost' 
DB_USER = 'root' 
DB_PASSWORD = ''  # INPUT_REQUIRED {Set the database password} 
DB_NAME = 'feedback_db' 
DB_PORT = 3307 
 
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