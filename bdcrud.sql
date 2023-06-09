PGDMP     )                    {            crud    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16576    crud    DATABASE     {   CREATE DATABASE crud WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE crud;
                postgres    false            �            1259    16592 
   embalagens    TABLE     �   CREATE TABLE public.embalagens (
    cdembalagem integer NOT NULL,
    descricao character varying(50) NOT NULL,
    quantidade integer NOT NULL,
    cdproduto integer
);
    DROP TABLE public.embalagens;
       public         heap    postgres    false            �            1259    16591    embalagens_cdembalagem_seq    SEQUENCE     �   CREATE SEQUENCE public.embalagens_cdembalagem_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.embalagens_cdembalagem_seq;
       public          postgres    false    217            	           0    0    embalagens_cdembalagem_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.embalagens_cdembalagem_seq OWNED BY public.embalagens.cdembalagem;
          public          postgres    false    216            �            1259    16585    produtos    TABLE     O  CREATE TABLE public.produtos (
    cdproduto integer NOT NULL,
    descricao character varying(50) NOT NULL,
    marca character varying(50) NOT NULL,
    fabricante character varying(50) NOT NULL,
    preco_custo double precision NOT NULL,
    preco_venda double precision NOT NULL,
    codigo_barra character varying(50) NOT NULL
);
    DROP TABLE public.produtos;
       public         heap    postgres    false            �            1259    16584    produtos_cdproduto_seq    SEQUENCE     �   CREATE SEQUENCE public.produtos_cdproduto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.produtos_cdproduto_seq;
       public          postgres    false    215            
           0    0    produtos_cdproduto_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.produtos_cdproduto_seq OWNED BY public.produtos.cdproduto;
          public          postgres    false    214            k           2604    16595    embalagens cdembalagem    DEFAULT     �   ALTER TABLE ONLY public.embalagens ALTER COLUMN cdembalagem SET DEFAULT nextval('public.embalagens_cdembalagem_seq'::regclass);
 E   ALTER TABLE public.embalagens ALTER COLUMN cdembalagem DROP DEFAULT;
       public          postgres    false    217    216    217            j           2604    16588    produtos cdproduto    DEFAULT     x   ALTER TABLE ONLY public.produtos ALTER COLUMN cdproduto SET DEFAULT nextval('public.produtos_cdproduto_seq'::regclass);
 A   ALTER TABLE public.produtos ALTER COLUMN cdproduto DROP DEFAULT;
       public          postgres    false    215    214    215                      0    16592 
   embalagens 
   TABLE DATA           S   COPY public.embalagens (cdembalagem, descricao, quantidade, cdproduto) FROM stdin;
    public          postgres    false    217   �                  0    16585    produtos 
   TABLE DATA           s   COPY public.produtos (cdproduto, descricao, marca, fabricante, preco_custo, preco_venda, codigo_barra) FROM stdin;
    public          postgres    false    215   4                  0    0    embalagens_cdembalagem_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.embalagens_cdembalagem_seq', 33, true);
          public          postgres    false    216                       0    0    produtos_cdproduto_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.produtos_cdproduto_seq', 24, true);
          public          postgres    false    214            o           2606    16597    embalagens pk_embalagens 
   CONSTRAINT     _   ALTER TABLE ONLY public.embalagens
    ADD CONSTRAINT pk_embalagens PRIMARY KEY (cdembalagem);
 B   ALTER TABLE ONLY public.embalagens DROP CONSTRAINT pk_embalagens;
       public            postgres    false    217            m           2606    16590    produtos pk_produtos 
   CONSTRAINT     Y   ALTER TABLE ONLY public.produtos
    ADD CONSTRAINT pk_produtos PRIMARY KEY (cdproduto);
 >   ALTER TABLE ONLY public.produtos DROP CONSTRAINT pk_produtos;
       public            postgres    false    215            p           2606    16611 !   embalagens fk_embalagens_produtos    FK CONSTRAINT     �   ALTER TABLE ONLY public.embalagens
    ADD CONSTRAINT fk_embalagens_produtos FOREIGN KEY (cdproduto) REFERENCES public.produtos(cdproduto) NOT VALID;
 K   ALTER TABLE ONLY public.embalagens DROP CONSTRAINT fk_embalagens_produtos;
       public          postgres    false    3181    215    217               �   x�U�M
�@��/���$��.Ep�	�	V���.��x/f槅~��I�T8i�G�3��du4v8�sx��.�{K����z\4���䍹�Z�����<���3e%7x� ��Z|ѹ"qЌ-g!a��K4YZ_w["�!�:B          7   x�32�LN8��i�F@ "LMM����Ȅ3	��
 ihdlbj����� 9�^     