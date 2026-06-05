INSERT INTO urls (
    id,
    title,
    original_url,
    slug,
    url_status_id,
    created_by,
    created_date,
    delete_flag
)
VALUES
(
    100,
    'Valid Url',
    'https://google.com',
    'valid-slug',
    1,
    1,
    '2026-01-01 00:00:00',
    false
),
(
    101,
    'Inactive Url',
    'https://github.com',
    'inactive-slug',
    2,
    1,
    '2026-01-01 00:00:00',
    false
);