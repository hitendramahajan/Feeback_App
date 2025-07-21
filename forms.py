from flask_wtf import FlaskForm 
from wtforms import StringField, TextAreaField, IntegerField, SubmitField 
from wtforms.validators import DataRequired, Email, NumberRange 
 
class FeedbackForm(FlaskForm): 
    first_name = StringField('First Name', validators=[DataRequired()]) 
    last_name = StringField('Last Name', validators=[DataRequired()]) 
    email = StringField('Email', validators=[DataRequired(), Email()]) 
    phone = StringField('Phone Number', validators=[DataRequired()]) 
    comments = TextAreaField('Comments', validators=[DataRequired()]) 
    rating = IntegerField('Star Rating', validators=[DataRequired(), NumberRange(min=1, max=5)]) 
    submit = SubmitField('Submit Feedback')