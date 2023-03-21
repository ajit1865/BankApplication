import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must
import org.scalatest.matchers.must.Matchers.not
import org.scalatest.matchers.should.Matchers

class BankingTest extends AnyFlatSpec with Matchers {
  val bankObject = new Banking1
  "it" should "match with account" in {
    val account = bankObject.createAccount(1000)
    println(account)

  }
  //  println(bankObject.listAccounts())
  "Banking" should "create a new account" in {
    val accountNumber = bankObject.createAccount(1000.0)
    bankObject.listAccounts() shouldBe accountNumber
  }
  //
  it should "list all accounts with balance" in {
    // val app = new Banking()
    val accountNumber1 = bankObject.createAccount(1000.0)
    bankObject.listAccounts() should be(accountNumber1)
  }
  //
  it should "fetch account balance using account number" in {
    //  val app = new Banking()
    val accountNumber = bankObject.createAccount(1000.0).keys.toList(0)
    //      println(accountNumber)
    //  bankObject.fetchAccountBalance(accountNumber) should be (Some(1000.0))
    bankObject.fetchAccountBalance(accountNumber) shouldBe 1000.0
  }
  //
  it should "update account balance based on a list of transactions" in {
    val acc = bankObject.createAccount(5000.0)
    val transactions = List(
      Transactions(1l, acc.keys.toList(0), "credit", 1000.0),
      // Transactions(2l, acc.keys.toList(0), "debit", 2000.0)
    )
    bankObject.updatedBalance(transactions).values.toList(0) shouldBe 2000.0

  }
  //
  it should "delete account using account number" in {
    //  val app = new Banking()
    val acc = bankObject.createAccount(5000.0)
    val accountNumber = bankObject.createAccount(1000.0)
    bankObject.deleteAccount(acc.keys.toList(0)) should be(true)

  }
}