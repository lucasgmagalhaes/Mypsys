var CNPJdaEmpresa = JSON.parse(localStorage.getItem('dados')).CNPJ;

window.onload = () => {
  imprimeTabela(JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`)).produtos);

  document.querySelector('#cancela').addEventListener('click',limparTela);
  document.querySelector('.executa_pesquisa').addEventListener('click',pesquisarProdutos);
}

function leDados() {
  let strDados = localStorage.getItem(`${CNPJdaEmpresa}`);
  let objDados = {};
  if (strDados) {
    objDados = JSON.parse(strDados);
  }
  else {
    objDados = {
      produtos: [{ descricao: "", codigo: "", categoria: "", quantidade: "",valor: "", armazem: "", estante: "", prateleira: "", posicao: "" }]}
    }
  return objDados;
}

function pesquisarProdutos() {
  let db = JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`));
  let dados = [];
  let pesq = document.querySelector('#campo_pesquisa').value;
  for (i = 0; i < db.produtos.length; i++) {
      if (db.produtos[i].descricao.includes(pesq)) {
          dados.push(db.produtos[i]);
        }
  }
  console.log(dados);
  imprimeTabela(dados);
}

function imprimeTabela(dados) {
  let tabela = document.querySelector('#corpoTabela');
  let strHtml = '';
  console.log('dados.length = ' + dados.length);
  for (i = 0; i < dados.length; i++) {
    let obj = dados[i];
    console.log("objeto = ");
    console.log(obj);
    strHtml += `
      <tr id="linha${i}" onclick="preencherBox(${i})">
        <td id="cod${i}" scope="row">${dados[i].codigo}</td>
        <td id="des${i}">${dados[i].descricao}</td>
        <td id="qtd${i}">${dados[i].quantidade}</td>
        <td id="val${i}">${dados[i].valor}</td>
        <td            >${dados[i].categoria}</td>
        <td            >${dados[i].armazem}</td>
        <td            >${dados[i].estante}</td>
        <td            >${dados[i].prateleira}</td>
        <td            >${dados[i].posicao}</td>
      </tr>
      `
  }
  tabela.innerHTML = strHtml;
}

function preencherBox(n) {
  console.log(n);
  let box = document.querySelector('.box_descricao');
  let cod = document.querySelector(`#cod${n}`).innerHTML;
  let des = document.querySelector(`#des${n}`).innerHTML;
  let qtd = document.querySelector(`#qtd${n}`).innerHTML;
  let val = document.querySelector(`#val${n}`).innerHTML;
  
  /* Codigo imagem 
  <img src="https://picsum.photos/200" alt="foto-produto" id="foto-produto"></img>*/
  
  let novoBox = `
    
    <span id="valor"><strong>Valor: </strong>${val}</span>
    <span id="descricao"><strong>Descrição: </strong>${des}</span>
    <span id="codigo"><strong>Código: </strong>${cod}</span>
    <div class="quantidade">
      <strong>Quantidade: </strong>
      <input id="quantidade" value="1">
      <button onclick="acr(1, ${n})"  id="mais">+</button>
      <button onclick="acr(-1, ${n})" id="menos">-</button>
    </div>
    <button class="btn btn-success confirma" id="confirma"><i class="fas fa-check"></i></button>
    <button class="btn btn-secondary cancela" id="cancela"><i class="fas fa-times"></i></button>
  `;
  box.innerHTML = novoBox;

  document.querySelector('#confirma').addEventListener('click',function()
    {vender(des,cod)});


}

function acr(n, i) {
  let campo = document.querySelector('#quantidade').value;
  campo =+ campo + n;
  //Corresponde a quantidade inicial.TODO: renomear.
  let qtdMax = JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`)).produtos[i].quantidade; 

  console.log("campo <= qtdMax: ", campo <= qtdMax);
  if(campo <= qtdMax && campo >= 0){
    document.querySelector("#quantidade").setAttribute("value", campo);
    document.querySelector("#quantidade").style.border = "2px inset";
  }
  else
    document.querySelector("#quantidade").style.border = "2px inset red";
}

function vender(des,cod) {
  let objEstoque = JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`));
  let arrItens = objEstoque.produtos;
  let qtd = document.querySelector('#quantidade').value;
  for(let i = 0 ; i < arrItens.length ; i++) {
    if(arrItens[i].descricao == des && arrItens[i].codigo == cod) {
      arrItens[i].quantidade = arrItens[i].quantidade - qtd;
      console.log(arrItens[i].quantidade);
      if(arrItens[i].quantidade <= 0) {
        arrItens.splice(i,1);
        console.log(arrItens);
      }
    }
  }
  localStorage.setItem(`${CNPJdaEmpresa}`, JSON.stringify(objEstoque));
  imprimeTabela(arrItens);
  limparTela();
  alert("Venda realizada com sucesso!");
}


//! NÃO TÁ FUNCIONANDO 
function limparTela() {

  let boxDesc = document.querySelector('.box_descricao');
  let telaLimpa = `
    <span id="valor">
      <strong>Valor unitário: </strong>
      <span id="valor"></span>
    </span>
    <span id="descricao">
      <strong>Descrição: </strong>
    </span>
    <span id="codigo">
      <strong>Código: </strong>
    </span>
    
    <div class="quantidade">
      <strong>Quantidade: <br></strong>
      <input id="quantidade" value="0">
      <button id="mais" class="btn btn-light">
        <i class="fas fa-plus"></i>
      </button>
      <button id="menos" class="btn btn-light">
        <i class="fas fa-minus"></i>
      </button>
    </div>
    
    <button class="btn btn-success confirma" id="confirma"><i class="fas fa-check"></i></button>
    <button class="btn btn-secondary cancela" id="cancela"><i class="fas fa-times"></i></button>
  `;
  boxDesc.innerHTML = telaLimpa;
}


