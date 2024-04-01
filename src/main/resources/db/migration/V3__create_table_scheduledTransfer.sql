CREATE TABLE IF NOT EXISTS public.tb_scheduled(
  id_scheduled_transfer uuid NOT NULL,
  balance_transaction numeric(38, 2) NOT NULL,
  receiver_scheduled_transfer character varying(255) NOT NULL,
  sender_scheduled_transfer character varying(255) NOT NULL,
  status_transaction smallint NOT NULL,
  time_stamp TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

