<?php

require 'vendor/autoload.php';
include 'Sparrow.php';

// making an instance of sparrow (https://github.com/mikecao/sparrow)
$db = new Sparrow();
$db->setDb(new PDO('mysql:host=192.168.2.91;port=15896;dbname=windsor', 'root', 'amBWZSCnZfGLe9U8ZKCMDQEXrxRCCT'));

// GET => api/schools
Flight::route('GET /api/schools', function () use ($db) {
    Flight::json(
        $db->from('schools')
            ->many()
    );
});

// GET => api/schools/12
Flight::route('GET /api/schools/@id:[0-9]+', function ($id) use ($db) {
    Flight::json(
        $db->from('schools')
            ->where('id', $id)
            ->one()
    );
});

Flight::start();
