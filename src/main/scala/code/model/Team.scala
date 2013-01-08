package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.http.S

/**
 * The singleton that has methods for accessing the database
 */
object Team extends Team with LongKeyedMetaMapper[Team] with CRUDify[Long, Team] {
  override def dbTableName = "teams" 
	
	
  
  // comment this line out to require email validations
}

/**
 * An O-R mapped "User" class that includes first name, last name, password and we add a "Personal Essay" to it
 */
class Team extends LongKeyedMapper[Team] with IdPK {
  def getSingleton = Team // what's the "meta" server

  // define an additional field for a personal essay
  object TeamName extends MappedString(this,100)
  object Coach extends MappedString(this,100)
  object ShirtColor extends MappedString(this,100)
  object Manager extends MappedString(this,100)
  object Owner extends MappedString(this,100)
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

