// pegar CNPJ da empresa:
var CNPJdaEmpresa = JSON.parse(localStorage.getItem('dados')).CNPJ;

function existeArmazenado(key) {
    if (localStorage.getItem(key) == null) {
        r = false;
    } else {
        r = true;
    }

    return r;
}

function escreverLista() {
    let lista = document.getElementById('lista');
    let modais = document.getElementById('modais');
    let inHTMLlista = '';
    let inHTMLmodais = '';
    let dados = getAll();

    for (i = 1; i < dados.produtos.length; i++) {
        perc = Math.round((dados.produtos[i].quantidade/dados.produtos[i].valorInicial)*100);
        inHTMLlista += `<div class="card shadow rounded m-3">
          <div class="card-body">
              <div class="row align-items-center">
                  <div class="col-12 col-xl-2 col-lg-2 col-md-12 col-sm-12 my-auto">
                      <div class="h5 font-weight-bold badge badge-dark codigo" id="${dados.produtos[i].id}-cod">
                              ${dados.produtos[i].codigo}
                      </div>
                  </div>
                  <div class="col-12 col-xl-5 col-lg-5 col-md-12 col-sm-12">
                      <div class="h4 border rounded-pill border-color-mpsys">
                          <p id="${dados.produtos[i].id}-descricao">
                          ${dados.produtos[i].descricao}
                          </p>
                      </div>
                  </div>
                  <div class="col-12 col-xl-3 col-lg-3 col-md-12 col-sm-12 border border-thicker rounded border-secondary">
                      <div class="row justify-content-center">
                          <div class="col-6 col-xl-6 col-lg-6 col-md-4 col-sm-4" id="${dados.produtos[i].id}-quantidade"> <div class="d-inline font-weight-bold">Qtd.:</div> ${dados.produtos[i].quantidade}</div>
                          <div class="col-6 col-xl-6 col-lg-6 col-md-4 col-sm-4" id="${dados.produtos[i].id}-valor"><div class="d-inline font-weight-bold">Preço: R$ </div> ${dados.produtos[i].valor}</div>
                          
                      </div>
                      <div class="row"> 
                        <div class="col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12 font-weight-bold " id="${dados.produtos[i].id}-categoria">
                            <div class="d-inline font-weight-bolder ">Categoria:</div>  ${dados.produtos[i].categoria}</div>
                      </div>
                      <div class="row">
                          <div class="col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12 text-center ">
                              <a href="#" class="text-decoration-none badge badge-primary" data-toggle="modal"
                              data-target="#modal${dados.produtos[i].codigo}">
                                  + Detalhes
                              </a>
                          </div>
                      </div>
                  </div>
                  <div class="col-12 col-xl-2 col-lg-2 col-md-12 col-sm-12 mt-3">
                    <div class="row>
                        <div class="col-6">
                            <p id="${dados.produtos[i].id}-percentual" class="h3 font-weight-bold">
                                ${perc} %
                            </p>
                        </div>
                    </div>
                  </div>
              </div>
          </div>
      </div>`;

        inHTMLmodais += `<!-- Modal -->
      <div class="modal fade" id="modal${dados.produtos[i].codigo}" tabindex="-1" role="dialog"
          aria-labelledby="modal${dados.produtos[i].codigo}" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <h5 class="modal-title" id="modal${dados.produtos[i].codigo}">
                        ${dados.produtos[i].codigo} - ${dados.produtos[i].descricao}
                      </h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true" >&times;</span>
                      </button>
                  </div>
                  <div class="modal-body">
                      <div class="row">
                          <div class="col-7">
                              <p>
                                  <b>Código: </b> <a id="">${dados.produtos[i].codigo}</a> <br>
                                  <b>Descrição: </b> <p id="">${dados.produtos[i].descricao}</p>
                              </p>
                          </div>
                          <div class="col-5">
                              <p>
                                  <b>Categoria: </b><a id="">${dados.produtos[i].categoria}</a> <br>
                                  <b>Quantidade: </b><a id="">${dados.produtos[i].quantidade}</a> <br>
                                  <b>Valor Aq: </b><a id="">${dados.produtos[i].valor}</a> <br>
                                  <b>Armazém: </b><a id="">${dados.produtos[i].armazem}</a> <br>
                                  <b>Estante: </b><a id="">${dados.produtos[i].estante}</a> <br>
                                  <b>Prateleira: </b><a id="">${dados.produtos[i].prateleira}</a> <br>
                                  <b>Posição: </b><a id="">${dados.produtos[i].posicao}</a> <br>
                              </p>
                          </div>
                      </div>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                      <button type="button" class="btn btn-primary">Adicionar na Lista de Reembastecimento</button>
                  </div>
              </div>
          </div>
      </div>`;
    }

    lista.innerHTML = inHTMLlista;
    modais.innerHTML = inHTMLmodais;

    colorPerc();

}

function getAll() {
    return JSON.parse(localStorage.getItem(`${CNPJdaEmpresa}`));
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

function escreveConsulta(d) {
    let lista = document.getElementById('lista');
    let modais = document.getElementById('modais');
    let inHTMLlista;
    let inHTMLmodais;

    perc = Math.round((d['quantidade']/d['valorInicial'])*100);

    inHTMLlista = 
    `<div class="card shadow rounded m-3">
          <div class="card-body">
              <div class="row align-items-center">
                  <div class="col-12 col-xl-2 col-lg-2 col-md-12 col-sm-12 my-auto">
                      <div class="h5 font-weight-bold border rounded-pill border-color-mpsys" id="${d['id']}-cod">
                        ${d['codigo']}
                      </div>
                  </div>
                  <div class="col-12 col-xl-5 col-lg-5 col-md-12 col-sm-12">
                      <div class="h4">
                          <p id="${d['id']}-descricao" class="text-decoration-underline">
                            ${d['descricao']}
                          </p>
                      </div>
                  </div>
                  <div class="col-12 col-xl-3 col-lg-3 col-md-12 col-sm-12 border rounded border-dark">
                      <div class="row justify-content-center">
                          <div class="col-6 col-xl-6 col-lg-6 col-md-4 col-sm-4" id="${d['id']}-quantidade"> <div class="d-inline font-weight-bold">Qtd.:</div> ${d['quantidade']}</div>
                          <div class="col-6 col-xl-6 col-lg-6 col-md-4 col-sm-4" id="${d['id']}-valor"><div class="d-inline font-weight-bold">Preço: R$ </div> ${d['valor']}</div>
                          
                      </div>
                      <div class="row"> 
                        <div class="col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12 font-weight-bold " id="${d['id']}-categoria">
                            <div class="d-inline font-weight-bolder ">Categoria:</div>  ${d['categoria']}</div>
                      </div>
                      <div class="row">
                          <div class="col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12 text-center ">
                              <a href="#" class="text-decoration-none badge badge-primary" data-toggle="modal"
                              data-target="#modal${d['codigo']}">
                                  + Detalhes
                              </a>
                          </div>
                      </div>
                  </div>
                  <div class="col-12 col-xl-2 col-lg-2 col-md-12 col-sm-12 mt-3">
                    <div class="row>
                        <div class="col-6">
                            <p id="${d['id']}-percentual" class="h3 font-weight-bold">
                                ${perc} %
                            </p>
                        </div>
                    </div>
                  </div>
              </div>
          </div>
      </div>`;

    inHTMLmodais = `<!-- Modal -->
                        <div class="modal fade" id="modal${d['codigo']}" tabindex="-1" role="dialog"
                            aria-labelledby="modal${d['codigo']}" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="modal${d['codigo']}">
                                            ${d['codigo']} - ${d['descricao']}
                                        </h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true" >&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-7">
                                                <p>
                                                    <b>Código: </b> <a id="">${d['codigo']}</a> <br>
                                                    <b>Descrição: </b> <p id="">${d['descricao']}</p>
                                                </p>
                                            </div>
                                            <div class="col-5">
                                                <p>
                                                    <b>Categoria: </b><a id="">${d['categoria']}</a> <br>
                                                    <b>Quantidade: </b><a id="">${d['quantidade']}</a> <br>
                                                    <b>Valor: </b><a id="">${d['valor']}</a> <br>
                                                    <b>Armazém: </b><a id="">${d['armazem']}</a> <br>
                                                    <b>Estante: </b><a id="">${d['estante']}</a> <br>
                                                    <b>Prateleira: </b><a id="">${d['prateleira']}</a> <br>
                                                    <b>Posição: </b><a id="">${d['posicao']}</a> <br>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                        <button type="button" class="btn btn-primary">Adicionar na Lista de Reembastecimento</button>
                                    </div>
                                </div>
                            </div>
                        </div>`;

    lista.innerHTML = inHTMLlista;
    modais.innerHTML = inHTMLmodais;

    colorPerc();
}

function consultar() {
    let consulta = document.getElementById('consulta').value;
    let esp = document.getElementById('pesquisa').value;

    if (esp == "descricao") {
        d = getDadosByDesc(consulta);
    } else {
        d = getDadosByCod(consulta);
    }
    
    console.log(typeof(d));
    if(d == ''){
        let lista = document.getElementById('lista');
        let strError = `<div class="card mb-3">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-12 h4">
                                        Ops, ocorreu um erro :(. Verifique se está pesquisando pela coisa certa. <br>
                                            Se estiver, o produto não está cadastrado. Cadastre-o clicando <a href="../../html/inclusao em estoque/inclusao.html"> aqui </a>. 
                                    </div>
                                </div>
                            </div>
                        </div>`;

        lista.innerHTML = strError;
    }else{
        escreveConsulta(d);
    }
}

function colorPerc(){
    let dados = getAll();
    for (i = 1; i < dados.produtos.length; i++){
        let perc = Math.round((dados.produtos[i].quantidade/dados.produtos[i].valorInicial)*100); 
        if(perc > 50){
            document.getElementById(`${dados.produtos[i].id}-percentual`).classList.add("text-success");
        }
        else{
            document.getElementById(`${dados.produtos[i].id}-percentual`).classList.add("text-danger");            
        }
    }
}

if (existeArmazenado(`${CNPJdaEmpresa}`)) {
    escreverLista();
    document.getElementById('btnConsultar').addEventListener('click', consultar);
} else {
    let lista = document.getElementById('lista');
    let strError = `<div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12 h4">
                                    Não foram encontrados produtos cadastrados :( Cadastre clicando <a href="../../html/inclusao em estoque/inclusao.html"> aqui </a>. 
                                </div>
                            </div>
                        </div>
                    </div>`;

    lista.innerHTML = strError;
}