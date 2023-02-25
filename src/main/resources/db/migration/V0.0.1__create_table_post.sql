CREATE TABLE post (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    title VARCHAR NOT NULL,
    content VARCHAR NOT NULL,
    person_id UUID NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (id)
);

