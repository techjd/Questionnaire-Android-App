package com.techjd.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Responses::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun responsesDao(): responsesDao
}

/* SQL FOR CREATING TABLE IN BACKEND

CREATE TABLE `responses`.`questions_responses` ( `enrollment_number` INT(100) NOT NULL , `email_id` VARCHAR(512) NOT NULL , `1` CHAR(1) NOT NULL , `2` CHAR(1) NOT NULL , `3` CHAR(1) NOT NULL , `4` CHAR(1) NOT NULL , `5` CHAR(1) NOT NULL , `6` CHAR(1) NOT NULL , `7` CHAR(1) NOT NULL , `8` CHAR(1) NOT NULL , `9` CHAR(1) NOT NULL , `10` CHAR(1) NOT NULL , `11` CHAR(1) NOT NULL , `12` CHAR(1) NOT NULL , `13` CHAR(1) NOT NULL , `14` CHAR(1) NOT NULL , `15` CHAR(1) NOT NULL , `16` CHAR(1) NOT NULL , `17` CHAR(1) NOT NULL , `18` CHAR(1) NOT NULL , `19` CHAR(1) NOT NULL , `20` CHAR(1) NOT NULL , `21` CHAR(1) NOT NULL , `22` CHAR(1) NOT NULL , `23` CHAR(1) NOT NULL , `24` CHAR(1) NOT NULL , `25` CHAR(1) NOT NULL , `26` CHAR(1) NOT NULL , `27` CHAR(1) NOT NULL , `28` CHAR(1) NOT NULL , `29` CHAR(1) NOT NULL , `30` CHAR(1) NOT NULL , `31` CHAR(1) NOT NULL , `32` CHAR(1) NOT NULL , `33` CHAR(1) NOT NULL , `34` CHAR(1) NOT NULL , `35` CHAR(1) NOT NULL , `36` CHAR(1) NOT NULL , `37` CHAR(1) NOT NULL , `38` CHAR(1) NOT NULL , `39` CHAR(1) NOT NULL , `40` CHAR(1) NOT NULL , `41` CHAR(1) NOT NULL , `42` CHAR(1) NOT NULL , `43` CHAR(1) NOT NULL , `44` CHAR(1) NOT NULL , `45` CHAR(1) NOT NULL , `46` CHAR(1) NOT NULL , `47` CHAR(1) NOT NULL , `48` CHAR(1) NOT NULL , `49` CHAR(1) NOT NULL , `50` CHAR(1) NOT NULL , PRIMARY KEY (`enrollment_number`)) ENGINE = InnoDB;

INSERT INTO `questions_responses` (`enrollment_number`, `email_id`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30`, `31`, `32`, `33`, `34`, `35`, `36`, `37`, `38`, `39`, `40`, `41`, `42`, `43`, `44`, `45`, `46`, `47`, `48`, `49`, `50`) VALUES ('190010107036', '19cp.jaydeepsinhparmar@adit.ac.in', 'A', 'B', 'C', 'D', 'A', 'B', 'C', 'D', 'A', 'B', 'C', 'D', 'A', 'B', 'C', 'D', 'A', 'B', 'C', 'D', 'A', 'B', 'B', 'C', 'C', 'A', 'B', 'C', 'A', 'B', 'C', 'A', 'C', 'C', 'C', 'B', 'D', 'D', 'A', 'D', 'A', 'B', 'C', 'D', 'D', 'A', 'B', 'C', 'A', 'D');

INSERT INTO `questions_responses` (`enrollment_number`, `email_id`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, `10`, `11`, `12`, `13`, `14`, `15`, `16`, `17`, `18`, `19`, `20`, `21`, `22`, `23`, `24`, `25`, `26`, `27`, `28`, `29`, `30`, `31`, `32`, `33`, `34`, `35`, `36`, `37`, `38`, `39`, `40`, `41`, `42`, `43`, `44`, `45`, `46`, `47`, `48`, `49`, `50`) VALUES ('190010107045', '19cp.xyz@adit.ac.in', 'A', 'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'C', 'B', 'B', 'B', 'C', 'D', 'C', 'D', 'C', 'D', 'C', 'D', 'C', 'D', 'C', 'D', 'C', 'A', 'B', 'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'C', 'C', 'C', 'C', 'C', 'D', 'D', 'D', 'D', 'D', 'D', 'A'), ('190010107035', '19cp.abc@adit.ac.in', 'A', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'D', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'C', 'C', 'C')
 */