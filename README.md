# Banco-digital

## Funcionalidades

01 - Cadastrar Cliente

02 - Cadastrar Conta          

03 - Imprimir relatório de clientes 

04 - Imprimir relatório de contas 

05 - Depositar Corrente      

06 - Depositar Poupança   

07 - Transferir CC para Poupança        

## Gerar versão (target/banco-digital-1.0-SNAPSHOT.jar)

mvn clean install 

## Teste Unitário

mvn test

### Gerar relatório de testes (target/site/)

mvn surefire-report:report-only

mvn site -DgenerateReports=false

