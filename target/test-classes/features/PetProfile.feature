Feature: Funcionalidad Pet Buy
  Rule: Yo como owner quiero poder relizar la gestión
  de mi mascota en la tienda

    @crear
    Scenario Outline: Crear un nuevo pet en la store de manera satisfactoria
      Given que como owner, creo el request para crear el pet en la store
        | petId   | categoryId   | categoryName   | petName   | photUrls   | tagsId   | tagsName   | status   |
        | <petId> | <categoryId> | <categoryName> | <petName> | <photUrls> | <tagsId> | <tagsName> | <status> |
      When como owner envio el request para crear el pet
      Then como owner valido que se creo correctamente y con el estado <code>
      Examples:
        | petId | categoryId | categoryName | petName | photUrls                 | tagsId | tagsName | status    | code |
        | 11    | 0          | mascota      | thor    | /src/images/download.jpg | 0      | pets     | available | 200  |

    @obtener
    Scenario Outline:  Obtener un pet de la store de manera satisfactoria
      Given que como owner, creo el request para crear el pet en la store
        | petId   | categoryId   | categoryName   | petName   | photUrls   | tagsId   | tagsName   | status   |
        | <petId> | <categoryId> | <categoryName> | <petName> | <photUrls> | <tagsId> | <tagsName> | <status> |
      And como owner envio el request para crear el pet
      When como owner consulta a la aplicacion para obtener pet por id
      Then como owner valido que se muestra el pet y con el estado <code>
      Examples:
        | petId | categoryId | categoryName | petName | photUrls                 | tagsId | tagsName | status    | code |
        | 11    | 0          | mascota      | thor    | /src/images/download.jpg | 0      | pets     | available | 200  |

    @actualizar
    Scenario Outline: Actualizar un pet en la store de manera satisfactoria
      Given que como owner, creo el request para actualizar el pet en la store
        | petId   | categoryId   | categoryName   | petName   | photUrls   | tagsId   | tagsName   | status   |
        | <petId> | <categoryId> | <categoryName> | <petName> | <photUrls> | <tagsId> | <tagsName> | <status> |
      When como owner envio el request para actualizar el pet
      Then como owner valido que se actualizo correctamente y con el estado <code>
      Examples:
        | petId | categoryId | categoryName | petName | photUrls                 | tagsId | tagsName | status    | code |
        | 11    | 0          | aves         | juan    | /src/images/download.jpg | 0      | petito   | available | 200  |


    @eliminar
    Scenario Outline:  Eliminar un pet de la store de manera satisfactoria por su identificador
      When como owner realizo la eliminacion del pet por su id <idPet>
      Then como owner valido que se elimino correctamente con estado <code>
      Examples:
        | idPet | code |
        | 11    | 200  |