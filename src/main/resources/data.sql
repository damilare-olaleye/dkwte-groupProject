INSERT INTO dwte."user" 
(first_name, last_name, email, password, phone_number, role)
VALUES
('Jane', 'Doe', 'janeDoe@yahoo.com', 'disIsMyPassword13', '7893098746', 'Member');

INSERT INTO dwte."restaurant" 
(restaurant_address, restaurant_name, restaurant_id)
VALUES
('Sterling,VA', 'Thai Deelish', 1);

INSERT INTO dwte."review"
(review_id, author_id, rating, restaurant_name, review, review_title, submit_date, author_user_id)
VALUES 
(1, 2, '5', 'PANDA Express', 'taste good', 'taste good', '12-12-2021', 1);