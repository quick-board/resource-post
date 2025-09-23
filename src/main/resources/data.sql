-- 회원 작성자
INSERT INTO posts (title, content, board_id, profile_id, created_at)
VALUES
('전체 game', '{"type":"doc","content":[{"type":"paragraph","content":[{"type":"text","text":"회원작성글1"}]}]}', 1, 1, '2024-07-01 10:00:00'),
('전체 health', '{"type":"doc","content":[{"type":"paragraph","content":[{"type":"text","text":"회원작성글2"}]}]}', 1, 2, '2024-07-01 10:01:00');

-- 비회원 작성자
INSERT INTO posts (title, content, board_id, guest_password, created_at)
VALUES
('전체 game steam', '{"type":"doc","content":[{"type":"paragraph","content":[{"type":"text","text":"비회원작성글1"}]}]}', 1, '1234', '2024-07-01 10:02:00'),
('전체 health protein', '{"type":"doc","content":[{"type":"paragraph","content":[{"type":"text","text":"비회원작성글2"}]}]}', 1, 'abcd', '2024-07-01 10:03:00');

-- 회원이 좋아요 누름
INSERT INTO likes (post_id, profile_id, created_at)
VALUES
(1, 1, '2024-07-01 10:04:00'),
(1, 2, '2024-07-01 10:05:00');

-- 비회원이 좋아요 누름
INSERT INTO likes (post_id, guest_uuid, created_at)
VALUES
(1, '550e8400-e29b-41d4-a716-446655440000', '2024-07-01 10:06:00'),
(2, '660e8400-e29b-41d4-a716-446655440111', '2024-07-01 10:07:00');
