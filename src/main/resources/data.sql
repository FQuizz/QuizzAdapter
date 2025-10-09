INSERT INTO quizzes (quiz_id, title, description,visibility, create_by, create_at) VALUES
('e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47475', 'Math', 'Some description about this quiz','PUBLIC', 1, CURRENT_TIMESTAMP),
('e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47476', 'Physics', 'Some description about this quiz', 'PUBLIC',2, CURRENT_TIMESTAMP);

INSERT INTO questions (question_id, content,is_active,question_type,question_position,quiz_id) VALUES
('e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47477','2 + 4 = ?', true, 'SINGLE_CHOICE',1,'e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47475');

INSERT INTO choices (choice_id, content , is_correct, question_id) VALUES
('e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47477','6',true,'e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47477'),
('e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47478','5',false,'e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47477'),
('e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47479','4',false,'e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47477'),
('e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47480','3',false,'e8c9a7f2-3f1b-4f2c-a6a3-1e12cbe47477');