CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 email VARCHAR(255) NOT NULL UNIQUE,
 timezone VARCHAR(64) NOT NULL DEFAULT 'UTC',
 created_at TIMESTAMP NOT NULL DEFAULT now(),
 updated_at TIMESTAMP NOT NULL DEFAULT now(),
 deleted_at TIMESTAMP
 );

CREATE TABLE accounts (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 user_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
 name VARCHAR(120) NOT NULL,
 type VARCHAR(20) NOT NULL, -- BANK | CASH | CARD
 created_at TIMESTAMP NOT NULL DEFAULT now(),
 deleted_at TIMESTAMP
 );

CREATE TABLE categories (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
  parent_id UUID REFERENCES categories(id) ON DELETE RESTRICT,
  name VARCHAR(120) NOT NULL,
  icon VARCHAR(40),
  color VARCHAR(9),
  created_at TIMESTAMP NOT NULL DEFAULT now(),
  deleted_at TIMESTAMP
  );


 CREATE TABLE transactions (
 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
 user_id UUID NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
 account_id UUID NOT NULL REFERENCES accounts(id) ON DELETE RESTRICT,
 category_id UUID REFERENCES categories(id) ON DELETE RESTRICT,
 amount NUMERIC(19,4) NOT NULL,
 currency VARCHAR(3) NOT NULL DEFAULT 'INR',
 direction VARCHAR(7) NOT NULL ,
 note VARCHAR(500),
 occurred_at TIMESTAMP NOT NULL,
 created_at TIMESTAMP NOT NULL DEFAULT now(),
 updated_at TIMESTAMP NOT NULL DEFAULT now(),
 deleted_at TIMESTAMP
 );


CREATE INDEX idx_tx_user_occurred ON transactions (user_id, occurred_at DESC) WHERE deleted_at IS NULL;



