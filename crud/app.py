from ctypes import c_double

from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.orm import relationship
import psycopg2



host = "localhost"
dbname = "crud"
user = "postgres"
password = "senha"
sslmode = "disable"

conn_string = "host={0} user={1} dbname={2} password={3} sslmode={4}".format(host, user, dbname, password, sslmode)
conexao = psycopg2.connect(conn_string)
cursor = conexao.cursor()

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:senha@localhost/crud'
db = SQLAlchemy(app)


class Produto(db.Model):
    __tablename__ = 'produtos'
    cdproduto = db.Column(db.Integer, primary_key=True, autoincrement=True)
    descricao = db.Column(db.String(50))
    marca = db.Column(db.String(20))
    fabricante = db.Column(db.String(30))
    preco_custo = db.Column(db.Float)
    preco_venda = db.Column(db.Float)
    codigo_barra = db.Column(db.String(13))


    def __init__(self, cdproduto, descricao, marca, fabricante, preco_custo,preco_venda, codigo_barra):
        self.cdproduto = cdproduto
        self.descricao = descricao
        self.marca = marca
        self.fabricante = fabricante
        self.preco_custo = preco_custo
        self.preco_venda = preco_venda
        self.codigo_barra = codigo_barra

    def to_dict(self):
        return {'cdproduto': self.cdproduto, 'descricao': self.descricao, 'marca': self.marca, 'fabricante': self.fabricante, 'preco_custo': self.preco_custo, 'preco_venda':self.preco_venda, 'codigo_barra': self.codigo_barra}


class Embalagem(db.Model):
    __tablename__ = 'embalagens'
    cdembalagem = db.Column(db.Integer, primary_key=True,autoincrement=True)
    descricao = db.Column(db.String(50))
    quantidade = db.Column(db.Integer)
    cdproduto = db.Column(db.Integer, db.ForeignKey('produtos.cdproduto'))
    produto = db.relationship('Produto', backref='embalagens')


    def __init__(self, cdembalagem, descricao, quantidade, cdproduto):
        self.cdembalagem= cdembalagem
        self.descricao = descricao
        self.quantidade = quantidade
        self.cdproduto = cdproduto

    def to_dict(self):
        return {'cdembalagem': self.cdembalagem, 'descricao': self.descricao, 'quantidade': self.quantidade, 'cdproduto': self.cdproduto}


with app.app_context():
    db.create_all()




@app.route('/produtos', methods=['GET'])
def get_produtos():
    produtos = Produto.query.all()
    return jsonify([produto.to_dict() for produto in produtos])

@app.route('/produtos', methods=['POST'])
def create_produto():
    data = request.get_json()
    print(data)
    produto = Produto(cdproduto=None, descricao=data['descricao'], marca=data['marca'], fabricante=data['fabricante'], preco_custo=data['preco_custo'], preco_venda=data['preco_venda'], codigo_barra=data['codigo_barra'])
    db.session.add(produto)
    db.session.commit()
    return jsonify(produto.to_dict())

@app.route('/produtos/<int:cdproduto>', methods=['GET'])
def get_produto(cdproduto):
    produto = Produto.query.get_or_404(cdproduto)
    return jsonify(produto.to_dict())

@app.route('/produtos/<int:cdproduto>', methods=['PUT'])
def update_produto(cdproduto):
    data = request.get_json()
    produto = Produto.query.get_or_404(cdproduto)
    produto.descricao = data['descricao']
    produto.marca = data['marca']
    produto.fabricante = data['fabricante']
    produto.preco_custo = data['preco_custo']
    produto.preco_venda = data['preco_venda']
    produto.codigo_barra = data['codigo_barra']
    db.session.commit()
    return jsonify(produto.to_dict())

@app.route('/produtos/<int:cdproduto>', methods=['DELETE'])
def delete_produto(cdproduto):
    produto = Produto.query.get_or_404(cdproduto)
    db.session.delete(produto)
    db.session.commit()
    return '', 204

@app.route('/produtos/<int:cdproduto>/embalagens', methods=['GET'])
def get_embalagens(cdproduto):
    produto = Produto.query.get_or_404(cdproduto)
    return jsonify([embalagem.to_dict() for embalagem in produto.embalagens])

@app.route('/produtos/<int:cdproduto>/embalagens', methods=['POST'])
def create_embalagem(cdproduto):
    data = request.get_json()
    print(data)
    descricao = data['descricao']
    quantidade = data['quantidade']
    embalagem = Embalagem(cdembalagem=None, descricao=descricao, quantidade=quantidade, cdproduto=cdproduto)
    db.session.add(embalagem)
    db.session.commit()
    return jsonify(embalagem.to_dict())

@app.route('/produtos/<int:cdproduto>/embalagens/<int:cdembalagem>', methods=['DELETE'])
def delete_embalagem(cdproduto, cdembalagem):
    embalagem = Embalagem.query.get_or_404(cdembalagem)
    db.session.delete(embalagem)
    db.session.commit()
    return '', 204

@app.route('/produtos/<int:cdproduto>/embalagens/<int:cdembalagem>', methods=['PUT'])
def update_embalagem(cdproduto,cdembalagem):
    data = request.get_json()
    embalagem = Embalagem.query.get_or_404(cdembalagem)
    embalagem.descricao = data['descricao']
    embalagem.quantidade = data['quantidade']
    db.session.commit()
    return jsonify(embalagem.to_dict())


if __name__ == '__main__':
    app.run(debug=True)
