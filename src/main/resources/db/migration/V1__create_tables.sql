CREATE TABLE IF NOT EXISTS public.tb_users(
  id uuid NOT NULL,
  user_balance numeric(38, 2) NOT NULL,
  user_name character varying(255) NOT NULL,
  user_document character varying(255) NOT NULL,
  user_email character varying(255) NOT NULL,
  user_password character varying(255) NOT NULL,
  user_type smallint NOT NULL
);


CREATE TABLE IF NOT EXISTS public.tb_transactions(
  id uuid NOT NULL,
  balance_transaction numeric(38, 2) NOT NULL,
  receiver_transaction bytea NOT NULL,
  sender_transaction bytea NOT NULL,
  status_transaction smallint NOT NULL,
  time_stamp date
);

