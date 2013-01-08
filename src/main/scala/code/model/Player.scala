package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.http.S

/**
 * The singleton that has methods for accessing the database
 */
object Player extends Player with LongKeyedMetaMapper[Player] with CRUDify[Long, Player] {
  override def dbTableName = "players" 
	
	
  
  // comment this line out to require email validations
}

/**
 * An O-R mapped "User" class that includes first name, last name, password and we add a "Personal Essay" to it
 */
class Player extends LongKeyedMapper[Player] with IdPK {
  def getSingleton = Player // what's the "meta" server

  // define an additional field for a personal essay
  object FirstName extends MappedString(this,100)
  object MidName extends MappedString(this,100)
  object LastName extends MappedString(this,100)
  object Role extends MappedString(this,100)
  object PlayFor extends MappedLongForeignKey(this, Team){
    override def validSelectValues={
      Full(Team.findAll.map(t=>(t.id.get,t.TeamName.get)))
    }
  }
  object Description extends MappedTextarea(this, 2048) {
    override def textareaRows  = 10
    override def textareaCols = 50
    override def displayName = "Description"
  }
  
  //Demo users can not be deleted and modified
  object isDemo extends MappedBoolean(this) {
    override def defaultValue=false
  }

 
}

