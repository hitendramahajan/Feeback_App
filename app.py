from flask import Flask, render_template, request, redirect, url_for, flash 
import logging 
import traceback 
from forms import FeedbackForm 
from db import get_db_connection 
 
# Initialize the Flask application 
app = Flask(__name__) 
 
# Configure logging 
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s') 
 
# Add a secret key for CSRF protection 
app.config['SECRET_KEY'] = 'your_secret_key_here'  # INPUT_REQUIRED {Set a secure secret key for CSRF protection} 
 
@app.route('/') 
def home(): 
    try: 
        logging.info("Rendering homepage.") 
        return render_template('index.html') 
    except Exception as e: 
        logging.error("Error rendering homepage: %s", traceback.format_exc()) 
        return "An error occurred while loading the homepage.", 500 
 
@app.route('/feedback-form', methods=['GET', 'POST']) 
def feedback_form(): 
    try: 
        logging.info("Accessing feedback form page.") 
        form = FeedbackForm() 
        if form.validate_on_submit(): 
            logging.info("Feedback form submitted successfully.") 
 
            # Extract form data 
            username = form.username.data 
            email = form.email.data 
            phone = form.phone.data 
            comments = form.comments.data 
            rating = form.rating.data 
 
            # Save feedback to the database 
            connection = get_db_connection() 
            if connection: 
                try: 
                    with connection.cursor() as cursor: 
                        sql = """ 
                        INSERT INTO feedback (username, email, phone, comments, rating) 
                        VALUES (%s, %s, %s, %s, %s) 
                        """ 
                        cursor.execute(sql, (username, email, phone, comments, rating)) 
                    connection.commit() 
                    logging.info("Feedback data saved to the database successfully.") 
                except Exception as e: 
                    logging.error("Error saving feedback to the database: %s", traceback.format_exc()) 
                    flash('An error occurred while saving your feedback. Please try again.', 'error') 
                finally: 
                    connection.close() 
 
            flash('Feedback submitted successfully!', 'success') 
            return redirect(url_for('feedback_list')) 
 
        return render_template('feedback_form.html', form=form) 
    except Exception as e: 
        logging.error("Error processing feedback form: %s", traceback.format_exc()) 
        flash('An error occurred while processing the feedback form. Please try again.', 'error') 
        return redirect(url_for('feedback_form')) 
 
@app.route('/feedback-list') 
def feedback_list(): 
    try: 
        logging.info("Accessing feedback list page.") 
        connection = get_db_connection() 
        feedback_entries = [] 
        if connection: 
            try: 
                with connection.cursor() as cursor: 
                    sql = "SELECT username, email, comments, rating FROM feedback" 
                    cursor.execute(sql) 
                    feedback_entries = cursor.fetchall() 
                logging.info("Feedback data retrieved successfully.") 
            except Exception as e: 
                logging.error("Error retrieving feedback from the database: %s", traceback.format_exc()) 
                flash('An error occurred while retrieving feedback data. Please try again later.', 'error') 
            finally: 
                connection.close() 
 
        return render_template('feedback_list.html', feedback_entries=feedback_entries) 
    except Exception as e: 
        logging.error("Error accessing feedback list page: %s", traceback.format_exc()) 
        flash('An error occurred while loading the feedback list page. Please try again later.', 'error') 
        return redirect(url_for('home')) 
 
if __name__ == '__main__': 
    try: 
        logging.info("Starting Flask application on port 5001.") 
        app.run(port=5001, debug=True) 
    except Exception as e: 
        logging.error("Error starting Flask application: %s", traceback.format_exc())