--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 12.4

-- Started on 2020-10-19 11:49:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE sbb;
--
-- TOC entry 2939 (class 1262 OID 16393)
-- Name: sbb; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE sbb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


\connect sbb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 216 (class 1259 OID 16653)
-- Name: groupidseq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.groupidseq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 205 (class 1259 OID 16424)
-- Name: passenger; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.passenger
(
    id         integer               NOT NULL,
    name       character varying(40),
    surname    character varying(40),
    birth_date date,
    username   character varying(40) NOT NULL,
    email      character varying(50)
);


--
-- TOC entry 204 (class 1259 OID 16422)
-- Name: passengers_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.passengers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 204
-- Name: passengers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.passengers_id_seq OWNED BY public.passenger.id;


--
-- TOC entry 207 (class 1259 OID 16447)
-- Name: route; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.route
(
    id   integer NOT NULL,
    name character varying(50)
);


--
-- TOC entry 213 (class 1259 OID 16528)
-- Name: route_details; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.route_details
(
    route_id      integer NOT NULL,
    station_id    integer NOT NULL,
    station_order integer NOT NULL,
    km            integer,
    type          integer
);


--
-- TOC entry 206 (class 1259 OID 16445)
-- Name: routes_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.routes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 206
-- Name: routes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.routes_id_seq OWNED BY public.route.id;


--
-- TOC entry 214 (class 1259 OID 16550)
-- Name: schedule_details; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.schedule_details
(
    route_id       integer,
    station_id     integer,
    station_order  integer,
    train_id       integer,
    line_id        integer NOT NULL,
    arrival_time   timestamp without time zone,
    departure_time timestamp without time zone,
    free_seats     integer,
    ordered_seats  integer,
    routegroupid   integer
);


--
-- TOC entry 215 (class 1259 OID 16563)
-- Name: schedule_details_line_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.schedule_details_line_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 215
-- Name: schedule_details_line_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.schedule_details_line_id_seq OWNED BY public.schedule_details.line_id;


--
-- TOC entry 209 (class 1259 OID 16460)
-- Name: station; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.station
(
    id   integer NOT NULL,
    name character varying(50)
);


--
-- TOC entry 208 (class 1259 OID 16458)
-- Name: stations_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.stations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 208
-- Name: stations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.stations_id_seq OWNED BY public.station.id;


--
-- TOC entry 211 (class 1259 OID 16491)
-- Name: ticket; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.ticket
(
    id           integer NOT NULL,
    price        integer,
    station_from integer,
    station_to   integer,
    line_id_from integer,
    holder_id    integer,
    routegroupid integer,
    line_id_to   integer
);


--
-- TOC entry 210 (class 1259 OID 16489)
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.ticket_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 210
-- Name: ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.ticket_id_seq OWNED BY public.ticket.id;


--
-- TOC entry 218 (class 1259 OID 16684)
-- Name: ticket_kipper; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.ticket_kipper
(
    name       character varying(40),
    surname    character varying(40),
    birth_date date,
    id         integer NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 16687)
-- Name: ticket_kipper_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.ticket_kipper_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 219
-- Name: ticket_kipper_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.ticket_kipper_id_seq OWNED BY public.ticket_kipper.id;


--
-- TOC entry 203 (class 1259 OID 16404)
-- Name: train; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.train
(
    id       integer NOT NULL,
    capacity integer,
    type     character varying(40),
    number   character varying(40),
    velocity integer
);


--
-- TOC entry 212 (class 1259 OID 16515)
-- Name: train_has_schedule_and_route; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.train_has_schedule_and_route
(
    train_id       integer                     NOT NULL,
    route_id       integer                     NOT NULL,
    departure_time timestamp without time zone NOT NULL,
    ordered_seats  integer,
    free_seats     integer,
    routegroupid   integer                     NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 16659)
-- Name: train_has_schedule_and_route_routegroupid_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.train_has_schedule_and_route_routegroupid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 217
-- Name: train_has_schedule_and_route_routegroupid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.train_has_schedule_and_route_routegroupid_seq OWNED BY public.train_has_schedule_and_route.routegroupid;


--
-- TOC entry 202 (class 1259 OID 16402)
-- Name: trains_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.trains_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 202
-- Name: trains_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.trains_id_seq OWNED BY public.train.id;


--
-- TOC entry 2736 (class 2604 OID 16427)
-- Name: passenger id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.passenger
    ALTER COLUMN id SET DEFAULT nextval('public.passengers_id_seq'::regclass);


--
-- TOC entry 2737 (class 2604 OID 16450)
-- Name: route id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.route
    ALTER COLUMN id SET DEFAULT nextval('public.routes_id_seq'::regclass);


--
-- TOC entry 2741 (class 2604 OID 16565)
-- Name: schedule_details line_id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedule_details
    ALTER COLUMN line_id SET DEFAULT nextval('public.schedule_details_line_id_seq'::regclass);


--
-- TOC entry 2738 (class 2604 OID 16463)
-- Name: station id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.station
    ALTER COLUMN id SET DEFAULT nextval('public.stations_id_seq'::regclass);


--
-- TOC entry 2739 (class 2604 OID 16494)
-- Name: ticket id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket
    ALTER COLUMN id SET DEFAULT nextval('public.ticket_id_seq'::regclass);


--
-- TOC entry 2742 (class 2604 OID 16689)
-- Name: ticket_kipper id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket_kipper
    ALTER COLUMN id SET DEFAULT nextval('public.ticket_kipper_id_seq'::regclass);


--
-- TOC entry 2735 (class 2604 OID 16407)
-- Name: train id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.train
    ALTER COLUMN id SET DEFAULT nextval('public.trains_id_seq'::regclass);


--
-- TOC entry 2740 (class 2604 OID 16661)
-- Name: train_has_schedule_and_route routegroupid; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.train_has_schedule_and_route
    ALTER COLUMN routegroupid SET DEFAULT nextval('public.train_has_schedule_and_route_routegroupid_seq'::regclass);


--
-- TOC entry 2919 (class 0 OID 16424)
-- Dependencies: 205
-- Data for Name: passenger; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.passenger (id, name, surname, birth_date, username, email)
VALUES (1, 'Vera', 'Matveeva', '1998-04-17', 'vera.matveeva.9898', 'vera.matveeva.9898@mail.ru');


--
-- TOC entry 2921 (class 0 OID 16447)
-- Dependencies: 207
-- Data for Name: route; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.route (id, name)
VALUES (1, 'Vyborgskoe');
INSERT INTO public.route (id, name)
VALUES (3, 'Ladojskoe');
INSERT INTO public.route (id, name)
VALUES (2, 'Tallinsk');
INSERT INTO public.route (id, name)
VALUES (7, 'TestRoute');
INSERT INTO public.route (id, name)
VALUES (8, 'Test2');
INSERT INTO public.route (id, name)
VALUES (9, 'RouteTest');
INSERT INTO public.route (id, name)
VALUES (10, 'TTT');


--
-- TOC entry 2927 (class 0 OID 16528)
-- Dependencies: 213
-- Data for Name: route_details; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (1, 1, 1, 0, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (1, 3, 2, 5, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (1, 4, 3, 10, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (2, 1, 1, 0, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (2, 2, 2, 3, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (2, 5, 3, 6, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (3, 3, 1, 0, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (3, 4, 2, 4, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (1, 2, 4, 12, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (7, 4, 5, 30, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (7, 5, 4, 24, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (7, 2, 1, 0, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (7, 1, 3, 15, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (7, 3, 2, 7, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (7, 8, 6, 32, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (8, 2, 1, 0, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (1, 5, 5, 14, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (8, 3, 2, 3, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (8, 5, 3, 6, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (8, 4, 4, 9, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (8, 1, 5, 12, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (8, 8, 6, 15, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (9, 9, 1, 0, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (9, 10, 2, 8, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (9, 11, 3, 20, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (10, 12, 1, 0, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (10, 13, 2, 13, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (10, 14, 3, 18, 1);
INSERT INTO public.route_details (route_id, station_id, station_order, km, type)
VALUES (10, 15, 4, 30, 1);


--
-- TOC entry 2928 (class 0 OID 16550)
-- Dependencies: 214
-- Data for Name: schedule_details; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 1, 1, 23, 73, '2020-10-29 17:23:00', '2020-10-29 17:33:00', 10, 0, 6);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 2, 4, 23, 76, '2020-10-29 17:46:00', '2020-10-29 17:48:00', 10, 0, 6);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 5, 5, 23, 77, '2020-10-29 17:49:30', '2020-10-29 17:51:30', 10, 0, 6);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 5, 5, 23, 82, '2020-10-29 17:52:30', '2020-10-29 17:54:30', 10, 0, 7);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (8, 2, 1, 28, 83, '2020-10-16 10:49:00', '2020-10-16 10:59:00', 2, 0, 8);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (8, 3, 2, 28, 84, '2020-10-16 11:17:00', '2020-10-16 11:19:00', 2, 0, 8);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (8, 5, 3, 28, 85, '2020-10-16 11:37:00', '2020-10-16 11:39:00', 2, 0, 8);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (8, 4, 4, 28, 86, '2020-10-16 11:57:00', '2020-10-16 11:59:00', 2, 0, 8);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (8, 1, 5, 28, 87, '2020-10-16 12:17:00', '2020-10-16 12:19:00', 2, 0, 8);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (8, 8, 6, 28, 88, '2020-10-16 12:37:00', '2020-10-16 12:39:00', 2, 0, 8);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 3, 2, 23, 74, '2020-10-29 17:36:45', '2020-10-29 17:38:45', 9, 1, 6);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 4, 3, 23, 75, '2020-10-29 17:42:30', '2020-10-29 17:44:30', 9, 1, 6);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 1, 1, 23, 78, '2020-10-29 17:26:00', '2020-10-29 17:36:00', 9, 1, 7);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 2, 4, 23, 81, '2020-10-29 17:49:00', '2020-10-29 17:51:00', 9, 1, 7);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 3, 2, 23, 79, '2020-10-29 17:39:45', '2020-10-29 17:41:45', 8, 2, 7);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (1, 4, 3, 23, 80, '2020-10-29 17:45:30', '2020-10-29 17:47:30', 8, 2, 7);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (9, 9, 1, 29, 89, '2020-10-23 22:32:00', '2020-10-23 22:42:00', 3, 0, 9);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (9, 10, 2, 29, 90, '2020-10-23 22:54:00', '2020-10-23 22:56:00', 2, 1, 9);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (9, 11, 3, 29, 91, '2020-10-23 23:14:00', '2020-10-23 23:16:00', 2, 1, 9);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (10, 12, 1, 22, 92, '2020-10-20 19:50:00', '2020-10-20 20:00:00', 5, 0, 10);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (10, 13, 2, 22, 93, '2020-10-20 20:15:36', '2020-10-20 20:17:36', 5, 0, 10);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (10, 14, 3, 22, 94, '2020-10-20 20:23:36', '2020-10-20 20:25:36', 5, 0, 10);
INSERT INTO public.schedule_details (route_id, station_id, station_order, train_id, line_id, arrival_time,
                                     departure_time, free_seats, ordered_seats, routegroupid)
VALUES (10, 15, 4, 22, 95, '2020-10-20 20:39:59.999', '2020-10-20 20:41:59.999', 5, 0, 10);


--
-- TOC entry 2923 (class 0 OID 16460)
-- Dependencies: 209
-- Data for Name: station; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.station (id, name)
VALUES (2, 'station2');
INSERT INTO public.station (id, name)
VALUES (3, 'station3');
INSERT INTO public.station (id, name)
VALUES (5, 'Station5');
INSERT INTO public.station (id, name)
VALUES (4, 'Station4');
INSERT INTO public.station (id, name)
VALUES (1, 'Station1');
INSERT INTO public.station (id, name)
VALUES (8, 'testStation');
INSERT INTO public.station (id, name)
VALUES (9, 'TT1');
INSERT INTO public.station (id, name)
VALUES (10, 'TT2');
INSERT INTO public.station (id, name)
VALUES (11, 'TT3');
INSERT INTO public.station (id, name)
VALUES (12, 'T1');
INSERT INTO public.station (id, name)
VALUES (13, 'T2');
INSERT INTO public.station (id, name)
VALUES (14, 'T3');
INSERT INTO public.station (id, name)
VALUES (15, 'T4');


--
-- TOC entry 2925 (class 0 OID 16491)
-- Dependencies: 211
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.ticket (id, price, station_from, station_to, line_id_from, holder_id, routegroupid, line_id_to)
VALUES (4, 20, 3, 4, 79, 4, 7, 80);
INSERT INTO public.ticket (id, price, station_from, station_to, line_id_from, holder_id, routegroupid, line_id_to)
VALUES (5, 20, 3, 4, 74, 4, 6, 75);
INSERT INTO public.ticket (id, price, station_from, station_to, line_id_from, holder_id, routegroupid, line_id_to)
VALUES (6, 20, 1, 2, 78, 5, 7, 81);
INSERT INTO public.ticket (id, price, station_from, station_to, line_id_from, holder_id, routegroupid, line_id_to)
VALUES (7, 20, 10, 11, 90, 6, 9, 91);


--
-- TOC entry 2932 (class 0 OID 16684)
-- Dependencies: 218
-- Data for Name: ticket_kipper; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.ticket_kipper (name, surname, birth_date, id)
VALUES ('Onam', 'Osur', '1996-01-31', 1);
INSERT INTO public.ticket_kipper (name, surname, birth_date, id)
VALUES ('V', 'V', '1998-04-17', 2);
INSERT INTO public.ticket_kipper (name, surname, birth_date, id)
VALUES ('123', '123', '2020-10-16', 3);
INSERT INTO public.ticket_kipper (name, surname, birth_date, id)
VALUES ('V2', 'V2', '1998-04-30', 4);
INSERT INTO public.ticket_kipper (name, surname, birth_date, id)
VALUES ('Sa', 'Sa', '2009-03-05', 5);
INSERT INTO public.ticket_kipper (name, surname, birth_date, id)
VALUES ('Tu1', 'Tu1', '2011-06-16', 6);


--
-- TOC entry 2917 (class 0 OID 16404)
-- Dependencies: 203
-- Data for Name: train; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.train (id, capacity, type, number, velocity)
VALUES (23, 10, 'Lastochka', '14L', 80);
INSERT INTO public.train (id, capacity, type, number, velocity)
VALUES (22, 5, 'Regular', '11R', 50);
INSERT INTO public.train (id, capacity, type, number, velocity)
VALUES (24, 3, 'Sapsan', '20S', 100);
INSERT INTO public.train (id, capacity, type, number, velocity)
VALUES (28, 2, 'Regular', 'TestTrain', 10);
INSERT INTO public.train (id, capacity, type, number, velocity)
VALUES (29, 3, 'Regular', 'TestTrain2', 40);


--
-- TOC entry 2926 (class 0 OID 16515)
-- Dependencies: 212
-- Data for Name: train_has_schedule_and_route; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.train_has_schedule_and_route (train_id, route_id, departure_time, ordered_seats, free_seats,
                                                 routegroupid)
VALUES (23, 1, '2020-10-29 00:00:00', 0, 10, 5);
INSERT INTO public.train_has_schedule_and_route (train_id, route_id, departure_time, ordered_seats, free_seats,
                                                 routegroupid)
VALUES (23, 1, '2020-10-29 17:33:00', 0, 10, 6);
INSERT INTO public.train_has_schedule_and_route (train_id, route_id, departure_time, ordered_seats, free_seats,
                                                 routegroupid)
VALUES (23, 1, '2020-10-29 17:36:00', 0, 10, 7);
INSERT INTO public.train_has_schedule_and_route (train_id, route_id, departure_time, ordered_seats, free_seats,
                                                 routegroupid)
VALUES (28, 8, '2020-10-16 10:59:00', 0, 2, 8);
INSERT INTO public.train_has_schedule_and_route (train_id, route_id, departure_time, ordered_seats, free_seats,
                                                 routegroupid)
VALUES (29, 9, '2020-10-23 22:42:00', 0, 3, 9);
INSERT INTO public.train_has_schedule_and_route (train_id, route_id, departure_time, ordered_seats, free_seats,
                                                 routegroupid)
VALUES (22, 10, '2020-10-20 20:00:00', 0, 5, 10);


--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 216
-- Name: groupidseq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.groupidseq', 1, false);


--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 204
-- Name: passengers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.passengers_id_seq', 1, true);


--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 206
-- Name: routes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.routes_id_seq', 10, true);


--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 215
-- Name: schedule_details_line_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.schedule_details_line_id_seq', 95, true);


--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 208
-- Name: stations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.stations_id_seq', 15, true);


--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 210
-- Name: ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.ticket_id_seq', 7, true);


--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 219
-- Name: ticket_kipper_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.ticket_kipper_id_seq', 6, true);


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 217
-- Name: train_has_schedule_and_route_routegroupid_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.train_has_schedule_and_route_routegroupid_seq', 10, true);


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 202
-- Name: trains_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.trains_id_seq', 29, true);


--
-- TOC entry 2748 (class 2606 OID 16580)
-- Name: passenger passenger_email_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.passenger
    ADD CONSTRAINT passenger_email_key UNIQUE (email);


--
-- TOC entry 2750 (class 2606 OID 16547)
-- Name: passenger passenger_username_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.passenger
    ADD CONSTRAINT passenger_username_key UNIQUE (username);


--
-- TOC entry 2752 (class 2606 OID 16429)
-- Name: passenger passengers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.passenger
    ADD CONSTRAINT passengers_pkey PRIMARY KEY (id);


--
-- TOC entry 2768 (class 2606 OID 16549)
-- Name: route_details route_details_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.route_details
    ADD CONSTRAINT route_details_pkey PRIMARY KEY (route_id, station_id, station_order);


--
-- TOC entry 2754 (class 2606 OID 16602)
-- Name: route route_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_name_key UNIQUE (name);


--
-- TOC entry 2756 (class 2606 OID 16452)
-- Name: route routes_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT routes_pkey PRIMARY KEY (id);


--
-- TOC entry 2772 (class 2606 OID 16567)
-- Name: schedule_details schedule_details_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedule_details
    ADD CONSTRAINT schedule_details_pkey PRIMARY KEY (line_id);


--
-- TOC entry 2758 (class 2606 OID 16600)
-- Name: station station_name_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.station
    ADD CONSTRAINT station_name_key UNIQUE (name);


--
-- TOC entry 2760 (class 2606 OID 16465)
-- Name: station stations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.station
    ADD CONSTRAINT stations_pkey PRIMARY KEY (id);


--
-- TOC entry 2774 (class 2606 OID 16697)
-- Name: ticket_kipper ticket_kipper_name_surname_birth_date_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket_kipper
    ADD CONSTRAINT ticket_kipper_name_surname_birth_date_key UNIQUE (name, surname, birth_date);


--
-- TOC entry 2776 (class 2606 OID 16691)
-- Name: ticket_kipper ticket_kipper_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket_kipper
    ADD CONSTRAINT ticket_kipper_pkey PRIMARY KEY (id);


--
-- TOC entry 2762 (class 2606 OID 16496)
-- Name: ticket ticket_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_pkey PRIMARY KEY (id);


--
-- TOC entry 2764 (class 2606 OID 16663)
-- Name: train_has_schedule_and_route train_has_schedule_and_route_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.train_has_schedule_and_route
    ADD CONSTRAINT train_has_schedule_and_route_pkey PRIMARY KEY (routegroupid);


--
-- TOC entry 2766 (class 2606 OID 16658)
-- Name: train_has_schedule_and_route train_has_schedule_and_route_route_id_train_id_departure_ti_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.train_has_schedule_and_route
    ADD CONSTRAINT train_has_schedule_and_route_route_id_train_id_departure_ti_key UNIQUE (route_id, train_id, departure_time);


--
-- TOC entry 2744 (class 2606 OID 16444)
-- Name: train trains_number_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.train
    ADD CONSTRAINT trains_number_key UNIQUE (number);


--
-- TOC entry 2746 (class 2606 OID 16409)
-- Name: train trains_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.train
    ADD CONSTRAINT trains_pkey PRIMARY KEY (id);


--
-- TOC entry 2770 (class 2606 OID 16609)
-- Name: route_details uq_routedetails; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.route_details
    ADD CONSTRAINT uq_routedetails UNIQUE (route_id, station_order);


--
-- TOC entry 2782 (class 2606 OID 16698)
-- Name: ticket holder_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT holder_fk FOREIGN KEY (holder_id) REFERENCES public.ticket_kipper (id);


--
-- TOC entry 2787 (class 2606 OID 16558)
-- Name: schedule_details route_details_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedule_details
    ADD CONSTRAINT route_details_fk FOREIGN KEY (route_id, station_id, station_order) REFERENCES public.route_details (route_id, station_id, station_order);


--
-- TOC entry 2785 (class 2606 OID 16531)
-- Name: route_details route_details_route_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.route_details
    ADD CONSTRAINT route_details_route_id_fkey FOREIGN KEY (route_id) REFERENCES public.route (id);


--
-- TOC entry 2786 (class 2606 OID 16536)
-- Name: route_details route_details_station_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.route_details
    ADD CONSTRAINT route_details_station_id_fkey FOREIGN KEY (station_id) REFERENCES public.station (id);


--
-- TOC entry 2789 (class 2606 OID 16669)
-- Name: schedule_details schedule_details_routegroupid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedule_details
    ADD CONSTRAINT schedule_details_routegroupid_fkey FOREIGN KEY (routegroupid) REFERENCES public.train_has_schedule_and_route (routegroupid);


--
-- TOC entry 2779 (class 2606 OID 16594)
-- Name: ticket ticket_line_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_line_id_fkey FOREIGN KEY (line_id_from) REFERENCES public.schedule_details (line_id);


--
-- TOC entry 2781 (class 2606 OID 16679)
-- Name: ticket ticket_line_id_to_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_line_id_to_fkey FOREIGN KEY (line_id_to) REFERENCES public.schedule_details (line_id);


--
-- TOC entry 2780 (class 2606 OID 16674)
-- Name: ticket ticket_routegroupid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_routegroupid_fkey FOREIGN KEY (routegroupid) REFERENCES public.train_has_schedule_and_route (routegroupid);


--
-- TOC entry 2777 (class 2606 OID 16581)
-- Name: ticket ticket_station_from_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_station_from_fkey FOREIGN KEY (station_from) REFERENCES public.station (id);


--
-- TOC entry 2778 (class 2606 OID 16586)
-- Name: ticket ticket_station_to_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_station_to_fkey FOREIGN KEY (station_to) REFERENCES public.station (id);


--
-- TOC entry 2784 (class 2606 OID 16541)
-- Name: train_has_schedule_and_route train_has_schedule_and_route_route_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.train_has_schedule_and_route
    ADD CONSTRAINT train_has_schedule_and_route_route_id_fkey FOREIGN KEY (route_id) REFERENCES public.route (id);


--
-- TOC entry 2788 (class 2606 OID 16574)
-- Name: schedule_details train_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedule_details
    ADD CONSTRAINT train_id_fk FOREIGN KEY (train_id) REFERENCES public.train (id);


--
-- TOC entry 2783 (class 2606 OID 16518)
-- Name: train_has_schedule_and_route train_schedule_train_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.train_has_schedule_and_route
    ADD CONSTRAINT train_schedule_train_id_fkey FOREIGN KEY (train_id) REFERENCES public.train (id);


-- Completed on 2020-10-19 11:49:42

--
-- PostgreSQL database dump complete
--

