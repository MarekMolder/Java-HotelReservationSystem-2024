# Hotel Booking System

## Requirements

### Hotel
- A hotel has multiple rooms.
- A hotel keeps track of rooms, customers, reservations, reviews, and the average rating.
- A hotel allows searching for available rooms based on date and/or type.
- A hotel allows sorting customers:
  - Customers with more bookings appear first.
  - If the number of bookings is the same, customers with higher review scores for the hotel appear first.

### Room
- Each room has a unique number.
- Each room belongs to only one hotel.
- Each room has a type that determines its price (at least three types must be implemented).
- Each room has a price.
- A room can be booked for a specific date if it is available.

### Customer
- A customer can book a room for a specific date.
- A room can only be booked if the customer has sufficient funds.
- A customer can cancel a booking.
- A customer can leave feedback for a hotel:
  - Feedback consists of a text review and a rating on a 5-point scale.
  - Only customers with a booking at the hotel can leave feedback.
- A customer has an overview of their bookings and feedback.

### Booking
- A booking is made for a specific room on a specific date.
- A booking can only be made if the room is available.
- A booking can be canceled.

### Review
- A review contains a text comment and a rating (1-5 scale).
- Only customers with a booking at the hotel can submit a review.

## Technologies Used
- Java
- Object-oriented programming (OOP) principles

## Usage
- Create a hotel and add rooms with different types.
- Allow customers to book rooms if they have sufficient funds.
- Implement booking cancellation functionality.
- Enable customers to leave reviews after a stay.
- Sort customers based on booking count and review scores.
