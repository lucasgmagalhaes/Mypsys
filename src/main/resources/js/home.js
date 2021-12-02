var CNPJdaEmpresa = JSON.parse(localStorage.getItem('dados')).CNPJ;

/* PESQUISA */

function pesquisarProdutos() {
    let db = JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`));
    let dados = [];
    let pesq = document.querySelector('.campo_pesquisa').value;
    for (i = 0; i < db.produtos.length; i++) {
      if(db.produtos[i].descricao != "") {
        if (db.produtos[i].descricao.includes(pesq)) {
          dados.push(db.produtos[i]);
        }
      } 
    }
    console.log(dados);
    imprimeTabela(dados);
  }

function imprimeTabela(dados) {
  let tabela = document.querySelector('#corpoTabela');
  let strHtml = ``;
  console.log('dados.length = ' + dados.length);
  for (i = 0; i < dados.length; i++) {
    let obj = dados[i];
    console.log("objeto = ");
    console.log(obj);
    strHtml += `
      <tr id="linha${i}">
        <td scope="row">${dados[i].codigo}</td>
        <td>${dados[i].descricao}</td>
        <td>${dados[i].valor}</td>
      </tr>
    `
  }
  tabela.innerHTML = strHtml;
}






function existeArmazenado(key) {
    if (localStorage.getItem(key) == null) {
        r = false;
    } else {
        r = true;
    }

    return r;
}

function getDadosByCod(cod) {
    let db = JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`));
    let dados = '';
    for (i = 0; i < db.produtos.length; i++) {
        if (db.produtos[i].codigo == cod) {
            dados = db.produtos[i];
        }
    }
    return dados;
}

function getDadosByDesc(desc) {
    let db = JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`));
    let dados = '';
    for (i = 0; i < db.produtos.length; i++) {
        if (db.produtos[i].descricao == desc) {
            dados = db.produtos[i];
        }
    }

    return dados;
}

function consultar() {
    let consulta = document.getElementById('consulta').value;
    let esp = document.getElementById('pesquisa').value;
    
    let titulo = document.querySelector(".titulo-consulta-home");
    titulo.innerHTML = `Produtos - Resultados por "${consulta}" `;


    if (esp == "descricao") {
        d = getDadosByDesc(consulta);
    } else {
        console.log(`Consulta ${consulta}`)
        d = getDadosByCod(consulta);
    }
    
    //alert(typeof(d));
    console.log(typeof(d));
    if(d == ''){
        let cardTable= document.getElementById('card-body-table-produtos');
        let strError = `<div class="row">
                            <div class="col-12 h4">
                                Ops, ocorreu um erro :(. Verifique se está pesquisando pela coisa certa. <br>
                                    Se estiver, o produto não está cadastrado. Cadastre-o clicando <a href="../../html/inclusao em estoque/inclusao.html"> aqui </a>. 
                            </div>
                        </div>`;

        cardTable.innerHTML = strError;
    }else{
        escreveConsulta(d);
    }
}

function escreveConsulta(d) {
    let cardTable= document.getElementById('card-body-table-produtos');
    let inHTMLtable = ``;

    inHTMLtable = ` <table class="table mb-0 table-striped table-bordered table-produtos-consulta">
                        <thead>
                            <tr>    
                                <th>Cód.</th>
                                <th>Descrição</th>
                                <th>Qtd.</th>
                                <th>Preço</th>
                            </tr>
                        </thead>
                        <tbody id="table-body-produtos">
                        <tr>
                            <th class="text-center">${d['codigo']}</th>
                            <td>${d['descricao']}</td>
                            <td>${d['quantidade']}</td>
                            <td>${d['valor']}</td>
                        </tr>
                        </tbody>
                    </table>`;
                    
    cardTable.innerHTML = inHTMLtable;

}

function initTable(){
    let table = document.getElementById('table-body-produtos');
    let inHTMLtable = '';
    let dados = getAll();

    for (i = 1; i < dados.produtos.length; i++) {
        inHTMLtable += `<tr>
                            <th>${dados.produtos[i].codigo}</th>
                            <td>${dados.produtos[i].descricao}</td>
                            <td>${dados.produtos[i].quantidade}</td>
                            <td>${dados.produtos[i].valor}</td>
                        </tr>`;
    }
    table.innerHTML = inHTMLtable;
}

function getAll() {
    return JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`));
}

window.onload = () => {
    if (existeArmazenado(`${CNPJdaEmpresa}`)) {
        initTable();
        document.querySelector('#btnConsultar').addEventListener('click', consultar);
        document.querySelector('#consulta').addEventListener('keydown',(e) => {
            if(e.keyCode == 13){
                consultar();
                e.preventDefault();
            }
        });
    } else {
        
        let cardTable= document.getElementById('card-body-table-produtos');
        let strError = `<div class="row">
                            <div class="col-12 h4">
                                Não existem produtos cadastrados :( Cadastre clicando <a href="../../html/inclusao em estoque/inclusao.html"> aqui </a>. 
                            </div>
                        </div>`;

        cardTable.innerHTML = strError;
    }
}