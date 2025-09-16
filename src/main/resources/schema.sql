DROP TABLE IF EXISTS Run;

CREATE TABLE IF NOT EXISTS Run (
    id uuid PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    started_on TIMESTAMP NOT NULL,
    completed_on TIMESTAMP NULL,
    meters INT NOT NULL CHECK (meters >= 0),
    location VARCHAR(10) NOT NULL CHECK (location IN ('INDOOR','OUTDOOR')),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);