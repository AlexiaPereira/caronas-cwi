angular.module('app').controller('RotinaController', ['$scope', 'RotinaService', function ($scope, RotinaService) {

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

    function listar() {
        RotinaService
            .listar()
            .then(response => {
                console.log(response);
            })
    }

    function procurar() {
        RotinaService
            .procurar()
            .then(response => {
                console.log(response);
            });
    }

    function selecionar(rotina) {
        if (isUndefinedOrNull(rotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .selecionar(rotina)
            .then(response => {
                console.log(response);
            });
    }

    function criar(rotina) {
        if (isUndefinedOrNull(rotina)) {
            console.log('undefined or null');
            return;
        } else if (rotina.motorista === true) {
            rotina.vagasTotais = $scope.total;
            rotina.vagasDisponiveis = $scope.disponivel;
        }
        // console.log(rotina);
        // for (var diaSemana in rotina.diaSemana) {
        //     console.log(typeof (diaSemana) + ": " + diaSemana);
        // }
        RotinaService
            .criar(rotina)
            .then(response => {
                console.log(response);
            });
    }

    function excluir(rotina) {
        if (isUndefinedOrNull(rotina)) {
            console.log('undefined or null');
            return;
        }
        RotinaService
            .excluir(rotina)
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
}]);