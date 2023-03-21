import scala.collection.mutable
import scala.util.Random

case class Transactions(transactionId: Long, accountNumber: Long, transactionType: String, amount: Double)

class Banking1 {
  val account: mutable.Map[Long, Double] = mutable.Map[Long, Double]()

  def createAccount(openingBalance: Double): mutable.Map[Long, Double] = {
    val accountNumber = Random.nextLong().abs
    account += (accountNumber -> openingBalance)
    account
  }

  def listAccounts(): mutable.Map[Long, Double] = {
    account
  }

  def fetchAccountBalance(accountNumber: Long): Double = {
    val balance = account(accountNumber)
    balance
  }

  def updatedBalance(transactions: List[Transactions]): mutable.Map[Long, Double] = {
    transactions.foreach { transaction =>
      val currentBalance = account.getOrElse(transaction.accountNumber, 0.0)
      val updateBalance = transaction.transactionType match {
        case "credit" => currentBalance + transaction.amount
        case "debit" => currentBalance - transaction.amount
        case _ => currentBalance
      }
      account += (transaction.accountNumber -> updateBalance)
    }
    account
  }

  def deleteAccount(accountNumber: Long): Boolean = {
    if (account.contains(accountNumber)) {
      account -= accountNumber
      true
    } else {
      false
    }
  }
}

object Banking extends App {
  val obj = new Banking1
  val acc = obj.createAccount(5000.0)
  val transactions = List(
    Transactions(1l, acc.keys.toList(0), "credit", 1000.0),
    Transactions(2l, acc.keys.toList(0), "debit", 4000.0)
  )
  val output = obj.updatedBalance(transactions)
  println(output)
}