from flask import Flask, render_template, request, redirect, url_for, flash, jsonify
import logging
import traceback
from forms import FeedbackForm
from db import get_db_connection

app = Flask(__name__)

logging.basicConfig(level=logging.INFO)

app.config['SECRET_KEY'] = 'your_secure_secret_key_here'

@app.errorhandler(Exception)
def handle_exception(e):
    logging.error("Unhandled exception: %s", traceback.format_exc())
    return jsonify(error="An unexpected error occurred. Please try again later."), 500

@app.route('/')
def home():
    try:
        logging.info("Rendering homepage.")
        return render_template('index.html')
    except Exception as e:
        logging.error("Error rendering homepage: %s", traceback.format_exc())
        return jsonify(error="An error occurred while loading the homepage."), 500

@app.route('/feedback-form', methods=['GET', 'POST'])
def feedback_form():
    try:
        logging.info("Accessing feedback form page.")
        form = FeedbackForm()
        if form.validate_on_submit():
            logging.info("Feedback form submitted successfully.")

            username = form.username.data
            email = form.email.data
            phone = form.phone.data
            comments = form.comments.data
            rating = form.rating.data

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
                finally:
                    connection.close()

            flash('Feedback submitted successfully!', 'success')
            return redirect(url_for('feedback_list'))

        return render_template('feedback_form.html', form=form)
    except Exception as e:
        logging.error("Error processing feedback form: %s", traceback.format_exc())
        return jsonify(error="An error occurred while processing the feedback form."), 500

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
            finally:
                connection.close()

        return render_template('feedback_list.html', feedback_entries=feedback_entries)
    except Exception as e:
        logging.error("Error accessing feedback list page: %s", traceback.format_exc())
        return jsonify(error="An error occurred while loading the feedback list page."), 500

if __name__ == '__main__':
    try:
        logging.info("Starting Flask application on port 5001.")
        app.run(port=5001, debug=True)
    except Exception as e:
        logging.error("Error starting Flask application: %s", traceback.format_exc())