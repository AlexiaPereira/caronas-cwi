angular.module('app').controller('RotinaController', ['$scope', 'RotinaService', '$q', function ($scope, RotinaService, $q) {

    // listar();
    // console.log($scope.rotinas);
listar();

    $scope.listar = listar;
    $scope.procurar = procurar;
    $scope.selecionar = selecionar;
    $scope.criar = criar;
    $scope.excluir = excluir;

    $scope.total = 0;
    $scope.disponivel = 0;
    $scope.addTotal = addTotal;
    $scope.subTotal = subTotal;
    $scope.addDisponivel = addDisponivel;
    $scope.subDisponivel = subDisponivel;

    $scope.distancia = 0;
    $scope.rotinasPassageiro = [];
    $scope.rotinasMotorista = [];

    // $scope.matches = [];
    // $scope.matches = [
    //     { 'nome': 'eu', 'foto': 'dois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' }
    // ];

    $scope.clique = clique;
    function clique(rotina) {
        console.log(rotina);
        procurarMatchs(rotina);
    }

    var destino = {
        lat: -29.794918,
        lng: -51.146509
    }

    console.log($scope.matches);

    function listar() {
        RotinaService
            .listar()
            .then(response => {
                let rotinas = response.data;
                rotinas.forEach(function(rotina) {
                  if(rotina.passageiro == false){
                    console.log('add mot');
                    $scope.rotinasMotorista.push(rotina);
                  }
                  else {
                    console.log('add pass');
                    $scope.rotinasPassageiro.push(rotina);
                  }
                });
            })
    }

    function procurar() {
        RotinaService
            .procurar()
            .then(response => {
                console.log(response);
            });
    }

    function selecionar(idRotina) {
        if (isUndefinedOrNull(idRotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .selecionar(idRotina)
            .then(response => {
                console.log(response);
            });
    }

    function criar(rotina) {
        debugger;
        if (isUndefinedOrNull(rotina)) {
            console.log('undefined or null');
            return;
        }
        console.log(rotina.horario);
        rotina.origem = origem;
        rotina.destino = { endereco: 'CWI SOFTWARE', latitude: destino.lat, longitude: destino.lng };
        rotina.vagasTotais = $scope.total || 0;
        rotina.vagasDisponiveis = $scope.disponivel || 0;
        rotina.duracao = 0;
        distanciaRotina({ lat: $scope.latitude, lng: $scope.longitude }, destino).then(res => {
            rotina.distancia = res;
            console.log('Rotina:' + rotina.distancia);

            var aux = [];
            for (var diaSemana in rotina.rotinaDiaSemanaList) {
                aux
                    .push({
                        'diaSemana':
                        { 'nome': diaSemana },
                        'vagasDisponiveis': rotina.vagasDisponiveis,
                    });
            }
            rotina.rotinaDiaSemanaList = aux;
            console.log(rotina.rotinaDiaSemanaList);
            // console.log(rotina);
            // for (var diaSemana in rotina.diaSemana) {
            //     console.log(typeof (diaSemana) + ": " + diaSemana);
            // }
            RotinaService
                .criar(rotina)
                .then(response => {
                    console.log(response);
                });

        });
    }

    function excluir(idRotina) {
        if (isUndefinedOrNull(idRotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .excluir(idRotina)
            .then(response => {
                console.log(response);
            });
    }

    function addTotal() {
        $scope.total++;
    }

    function subTotal() {
        if ($scope.disponivel === $scope.total) {
            subDisponivel();
            setTimeout(subTotal, 100);
        } else if ($scope.total > 0) {
            $scope.total--;
            $scope.$digest();
        }
    }

    function addDisponivel() {
        if ($scope.disponivel < $scope.total) {
            $scope.disponivel++;
        }
    }

    function subDisponivel() {
        if ($scope.disponivel > 0) {
            $scope.disponivel--;
        }
    }

    function isUndefinedOrNull(object) {
        return (angular.isUndefined(object) || object === null);
    }

    autoComplete();

    var matrizPassageiro = [];
    var listaMotorista = [];
    var matrizMotoristas = [];
    var matrizPassageiro = [];
    var listaDistanciaRotina = [];

    $scope.matches = [];
    $scope.procurarMatchs = procurarMatchs;
    function procurarMatchs(rotina) {
        debugger;
        function async() {
            return $q(function(resolve, reject) {
                setTimeout(function() {
                    verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
                }, 1000);
            });
        }
            // verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
            // .then
            var respostaMetodoPrimeirasVerificacoes = verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
            matrix(rotina, respostaMetodoPrimeirasVerificacoes)
                .then(res => {
                    $scope.respostaMatrix = res;
                    $scope.matchs = obterRotinasComMatchDistancia(rotinaPassageiro, respostaMatrix);
                });
            return matchs;
        };

    function verificarMatchHorarioEQuantidadeDeVagas(idRotina) {
        debugger;
        RotinaService
            .getRotinasMatchHorarioEComVaga(idRotina)
            .then(function (response) {
                $scope.listaDeRotinasMotorista = response.data;
                return listaDeRotinasMotorista;
            })
    };

    function matrix(rotinaPassageiro, listaDeRotinasMotorista) {
        debugger;
        var matrixDeferred = $q.defer();
        /*listaMotorista = [{idOrigem:{latitude:-30.0624354, longitude:-51.1749197}, idUsuario:5},
        {idOrigem:{latitude:-30.0153303, longitude:-51.1130727}, idUsuario:4},
        {idOrigem:{latitude:-23.5505199, longitude:-46.6333094}, idUsuario:6}]
        matrizPassageiro = [{lat:-29.7949175,lng:-51.1465092}];*/
        listaDeRotinasMotorista.forEach(function (motorista) {
            let objetoMotorista = { lat: motorista.idOrigem.latitude, lng: motorista.idOrigem.longitude };
            matrizMotoristas.push(objetoMotorista);
        });
        let objetoPassageiro = { lat: rotinaPassageiro.idOrigem.latitude, lng: rotinaPassageiro.idOrigem.longitude };
        matrizPassageiro.push(objetoPassageiro);

        new google.maps.DistanceMatrixService().getDistanceMatrix({
            origins: matrizMotoristas,
            destinations: matrizPassageiro,
            travelMode: 'DRIVING',
            unitSystem: google.maps.UnitSystem.METRIC,
            avoidHighways: false,
            avoidTolls: false
        }, function (response) {
            let matriz = response;
            let i = 0;
            for (var linha in matriz.rows) {
                var distanciaRetorno = matriz.rows[i].elements[0].distance.value;
                distancias.push({ motorista: listaMotorista[0], distancia: distanciaRetorno });
                i++;
            }
            matrixDeferred.resolve(distancias);
        })
        return matrixDeferred.promise;
    }

    function obterRotinasComMatchDistancia(rotinaPassageiro, listaDistanciaRotina) {
        RotinaService.getRotinasComMatchDistancia(rotinaPassageiro, listaDistanciaRotina).then(function (response) {
            listaDeMatchs = response.data;
        })
        return listaDeMatchs;
    };

    //API AUTOCOMPLETE
    function autoComplete() {
        $scope.autocomplete = new google.maps.places.Autocomplete(
            (document.getElementById('autocomplete'))
        );
        $scope.autocomplete.addListener('place_changed', pegarCoordenadas);
    }

    var origem;
    function pegarCoordenadas() {
        var place = $scope.autocomplete.getPlace();
        console.log(place);
        $scope.latitude = place.geometry.location.lat();
        $scope.longitude = place.geometry.location.lng();
        origem = { endereco: place.formatted_address, latitude: $scope.latitude, longitude: $scope.longitude };
    }


    function distanciaRotina(origemPassageiro, destinoPassageiro) {
        // debugger;
        let origem = [origemPassageiro];
        let destino = [destinoPassageiro];
        var deferred = $q.defer();
        new google.maps.DistanceMatrixService().getDistanceMatrix({
            origins: origem,
            destinations: destino,
            travelMode: 'DRIVING',
            unitSystem: google.maps.UnitSystem.METRIC,
            avoidHighways: false,
            avoidTolls: false
        }, function (response) {
            var matriz = response;
            var distanciaRetorno = matriz.rows[0].elements[0].distance.value;
            deferred.resolve(distanciaRetorno);
        })
        return deferred.promise;
    }

}]);

    // listar();
    // console.log($scope.rotinas);
listar();

    $scope.listar = listar;
    $scope.procurar = procurar;
    $scope.selecionar = selecionar;
    $scope.criar = criar;
    $scope.excluir = excluir;

    $scope.total = 0;
    $scope.disponivel = 0;
    $scope.addTotal = addTotal;
    $scope.subTotal = subTotal;
    $scope.addDisponivel = addDisponivel;
    $scope.subDisponivel = subDisponivel;

    $scope.distancia = 0;
    $scope.rotinasPassageiro = [];
    $scope.rotinasMotorista = [];

    // $scope.matches = [];
    // $scope.matches = [
    //     { 'nome': 'eu', 'foto': 'dois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' },
    //     { 'nome': 'eudois', 'foto': 'fotodois' }
    // ];

    $scope.clique = clique;
    function clique(rotina) {
        console.log(rotina);
        procurarMatchs(rotina);
    }

    var destino = {
        lat: -29.794918,
        lng: -51.146509
    }

    console.log($scope.matches);

    function listar() {
        RotinaService
            .listar()
            .then(response => {
                let rotinas = response.data;
                rotinas.forEach(function(rotina) {
                  if(rotina.passageiro == false){
                    console.log('add mot');
                    $scope.rotinasMotorista.push(rotina);
                  }
                  else {
                    console.log('add pass');
                    $scope.rotinasPassageiro.push(rotina);
                  }
                });
            })
    }

    function procurar() {
        RotinaService
            .procurar()
            .then(response => {
                console.log(response);
            });
    }

    function selecionar(idRotina) {
        if (isUndefinedOrNull(idRotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .selecionar(idRotina)
            .then(response => {
                console.log(response);
            });
    }

    function criar(rotina) {
        debugger;
        if (isUndefinedOrNull(rotina)) {
            console.log('undefined or null');
            return;
        }
        console.log(rotina.horario);
        rotina.origem = origem;
        rotina.destino = { endereco: 'CWI SOFTWARE', latitude: destino.lat, longitude: destino.lng };
        rotina.vagasTotais = $scope.total || 0;
        rotina.vagasDisponiveis = $scope.disponivel || 0;
        rotina.duracao = 0;
        distanciaRotina({ lat: $scope.latitude, lng: $scope.longitude }, destino).then(res => {
            rotina.distancia = res;
            console.log('Rotina:' + rotina.distancia);

            var aux = [];
            for (var diaSemana in rotina.rotinaDiaSemanaList) {
                aux
                    .push({
                        'diaSemana':
                        { 'nome': diaSemana },
                        'vagasDisponiveis': rotina.vagasDisponiveis,
                    });
            }
            rotina.rotinaDiaSemanaList = aux;
            console.log(rotina.rotinaDiaSemanaList);
            // console.log(rotina);
            // for (var diaSemana in rotina.diaSemana) {
            //     console.log(typeof (diaSemana) + ": " + diaSemana);
            // }
            RotinaService
                .criar(rotina)
                .then(response => {
                    console.log(response);
                });

        });
    }

    function excluir(idRotina) {
        if (isUndefinedOrNull(idRotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .excluir(idRotina)
            .then(response => {
                console.log(response);
            });
    }

    function addTotal() {
        $scope.total++;
    }

    function subTotal() {
        if ($scope.disponivel === $scope.total) {
            subDisponivel();
            setTimeout(subTotal, 100);
        } else if ($scope.total > 0) {
            $scope.total--;
            $scope.$digest();
        }
    }

    function addDisponivel() {
        if ($scope.disponivel < $scope.total) {
            $scope.disponivel++;
        }
    }

    function subDisponivel() {
        if ($scope.disponivel > 0) {
            $scope.disponivel--;
        }
    }

    function isUndefinedOrNull(object) {
        return (angular.isUndefined(object) || object === null);
    }

    autoComplete();

    var matrizPassageiro = [];
    var listaMotorista = [];
    var matrizMotoristas = [];
    var matrizPassageiro = [];
    var listaDistanciaRotina = [];

    $scope.matches = [];
    $scope.procurarMatchs = procurarMatchs;
    function procurarMatchs(rotina) {
        debugger;
        function async() {
            return $q(function(resolve, reject) {
                setTimeout(function() {
                    verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
                }, 1000);
            });
        }
            // verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
            // .then
            var respostaMetodoPrimeirasVerificacoes = verificarMatchHorarioEQuantidadeDeVagas(rotina.idRotina);
            matrix(rotina, respostaMetodoPrimeirasVerificacoes)
                .then(res => {
                    $scope.respostaMatrix = res;
                    $scope.matchs = obterRotinasComMatchDistancia(rotinaPassageiro, respostaMatrix);
                });
            return matchs;
        };

    function verificarMatchHorarioEQuantidadeDeVagas(idRotina) {
        debugger;
        RotinaService
            .getRotinasMatchHorarioEComVaga(idRotina)
            .then(function (response) {
                $scope.listaDeRotinasMotorista = response.data;
                return listaDeRotinasMotorista;
            })
    };

    function matrix(rotinaPassageiro, listaDeRotinasMotorista) {
        debugger;
        var matrixDeferred = $q.defer();
        /*listaMotorista = [{idOrigem:{latitude:-30.0624354, longitude:-51.1749197}, idUsuario:5},
        {idOrigem:{latitude:-30.0153303, longitude:-51.1130727}, idUsuario:4},
        {idOrigem:{latitude:-23.5505199, longitude:-46.6333094}, idUsuario:6}]
        matrizPassageiro = [{lat:-29.7949175,lng:-51.1465092}];*/
        listaDeRotinasMotorista.forEach(function (motorista) {
            let objetoMotorista = { lat: motorista.idOrigem.latitude, lng: motorista.idOrigem.longitude };
            matrizMotoristas.push(objetoMotorista);
        });
        let objetoPassageiro = { lat: rotinaPassageiro.idOrigem.latitude, lng: rotinaPassageiro.idOrigem.longitude };
        matrizPassageiro.push(objetoPassageiro);

        new google.maps.DistanceMatrixService().getDistanceMatrix({
            origins: matrizMotoristas,
            destinations: matrizPassageiro,
            travelMode: 'DRIVING',
            unitSystem: google.maps.UnitSystem.METRIC,
            avoidHighways: false,
            avoidTolls: false
        }, function (response) {
            let matriz = response;
            let i = 0;
            for (var linha in matriz.rows) {
                var distanciaRetorno = matriz.rows[i].elements[0].distance.value;
                distancias.push({ motorista: listaMotorista[0], distancia: distanciaRetorno });
                i++;
            }
            matrixDeferred.resolve(distancias);
        })
        return matrixDeferred.promise;
    }

    function obterRotinasComMatchDistancia(rotinaPassageiro, listaDistanciaRotina) {
        RotinaService.getRotinasComMatchDistancia(rotinaPassageiro, listaDistanciaRotina).then(function (response) {
            listaDeMatchs = response.data;
        })
        return listaDeMatchs;
    };

    //API AUTOCOMPLETE
    function autoComplete() {
        $scope.autocomplete = new google.maps.places.Autocomplete(
            (document.getElementById('autocomplete'))
        );
        $scope.autocomplete.addListener('place_changed', pegarCoordenadas);
    }

    var origem;
    function pegarCoordenadas() {
        var place = $scope.autocomplete.getPlace();
        console.log(place);
        $scope.latitude = place.geometry.location.lat();
        $scope.longitude = place.geometry.location.lng();
        origem = { endereco: place.formatted_address, latitude: $scope.latitude, longitude: $scope.longitude };
    }


    function distanciaRotina(origemPassageiro, destinoPassageiro) {
        // debugger;
        let origem = [origemPassageiro];
        let destino = [destinoPassageiro];
        var deferred = $q.defer();
        new google.maps.DistanceMatrixService().getDistanceMatrix({
            origins: origem,
            destinations: destino,
            travelMode: 'DRIVING',
            unitSystem: google.maps.UnitSystem.METRIC,
            avoidHighways: false,
            avoidTolls: false
        }, function (response) {
            var matriz = response;
            var distanciaRetorno = matriz.rows[0].elements[0].distance.value;
            deferred.resolve(distanciaRetorno);
        })
        return deferred.promise;
    }

}]);
    MapService.iniciarMapa(cwi);
  function mapaCWI(cwi) {

  mapaCWI(cwi);
  listar();
  var cwi = {lat: -29.7608, lng: -51.1522};
  }