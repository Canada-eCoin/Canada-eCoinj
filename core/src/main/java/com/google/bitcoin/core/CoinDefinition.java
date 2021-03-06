package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 8/13/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "Canada eCoin";
    public static final String coinTicker = "CDN";
    public static final String coinURIScheme = "Canadaecoin";
    public static final String cryptsyMarketId = "26";
    public static final String cryptsyMarketCurrency = "CDN";
    public static final String PATTERN_PRIVATE_KEY_START = "6";

    public static String lowerCaseCoinName() { return coinName.toLowerCase(); }

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://explore.canadaecoin.ca/";    //blockr.io
    public static final String BLOCKEXPLORER_ADDRESS_PATH = "address/info/";             //blockr.io path
    public static final String BLOCKEXPLORER_TRANSACTION_PATH = "tx/info/";              //blockr.io path
    public static final String BLOCKEXPLORER_BLOCK_PATH = "block/info/";                 //blockr.io path
    public static final String BLOCKEXPLORER_BASE_URL_TEST = BLOCKEXPLORER_BASE_URL_PROD;

    public static final String DONATION_ADDRESS = "DPdbL3n3Y3ypwVEvY3wABmpbjsd3AVqm5M";  //ramzimaalej donation BTC address

    enum CoinHash {
        SHA256,
        scrypt,
    };
    public static final CoinHash coinPOWHash = CoinHash.scrypt;

    public static boolean checkpointFileSupport = true;

    public static final int TARGET_TIMESPAN = (int)(1 * 24 * 60 * 60);  // 24hours per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(0.5 * 60);  // 30 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //2880 blocks

    public static final int getInterval(int height, boolean testNet) {
        return INTERVAL;      //2880
    }
    public static final int getIntervalCheckpoints() {
        return INTERVAL;    //2880
    }
    public static final int getTargetTimespan(int height, boolean testNet) {
        return TARGET_TIMESPAN;    //24hours
    }

    public static int spendableCoinbaseDepth = 100; //main.h: static const int COINBASE_MATURITY checked
    public static final BigInteger MAX_MONEY = BigInteger.valueOf(100000000).multiply(Utils.COIN); //main.h:  MAX_MONEY checked

    // TODO set dust limit
    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(100000);   // MIN_TX_FEE checked
    public static final BigInteger DUST_LIMIT = Utils.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 70002;          //version.h PROTOCOL_VERSION checked
    public static final int MIN_PROTOCOL_VERSION = 209;        //version.h MIN_PROTO_VERSION checked

    public static final int BLOCK_CURRENTVERSION = 1;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 1 * 1000 * 1000; // checked


    public static final boolean supportsBloomFiltering = false; //Requires PROTOCOL_VERSION 70002 in the client

    public static final int Port    = 34331;       //protocol.h GetDefaultPort(testnet=false) checked
    public static final int TestPort = 41331;     //protocol.h GetDefaultPort(testnet=true) checked

    //
    //  Production
    //
    public static final int AddressHeader = 28;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS checked
    public static final int p2shHeader = 5;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS checked
    public static final boolean allowBitcoinPrivateKey = true; //for backward compatibility with previous version of cancoin
    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xfdc4b9de;      //0xfd, 0xc4, 0xb9, 0xde

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex checked
    static public long genesisBlockTime = 1396006396L;                       //main.cpp: LoadBlockIndex checked
    static public long genesisBlockNonce = (800327);                         //main.cpp: LoadBlockIndex checked
    static public String genesisHash = "863626dadaef221e2e2f30ff3dacae44cabdae9e0028058072181b3fb675d94a"; //main.cpp: hashGenesisBlock checked
    static public int genesisBlockValue = 50;                                                              //main.cpp: LoadBlockIndex checked
    //taken from the raw data of the block explorer
    static public String genesisXInBytes = "04ffff001d01042b4a6f7365706820577269676874204a722e2077617320626f726e206f6e2074686973206461792031393036"; // checked
    static public String genessiXOutBytes = "04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f"; // checked

    //net.cpp strDNSSeed checked
    static public String[] dnsSeeds = new String[] {
            "seed.canadaecoin.ca"
    };

    public static int minBroadcastConnections = 1;   //0 for default; we need more peers.

    //
    // TestNet - cancoin - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 87;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST checked
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST checked
    public static final long testnetPacketMagic = 0xfcc1b7dc;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex checked
    static public long testnetGenesisBlockTime = 1395301765L;                       //main.cpp: LoadBlockIndex checked
    static public long testnetGenesisBlockNonce = (0);                         //main.cpp: LoadBlockIndex checked

    //main.cpp GetBlockValue(height, fee) checked
    public static final BigInteger GetBlockReward(int height)
    {
        BigInteger nSubsidy = Utils.toNanoCoins(15, 0);
        if(height <= 500)
        {
            nSubsidy = Utils.toNanoCoins(25, 0); //25
        }
        else if(height <= 1000)
        {
            nSubsidy   = Utils.toNanoCoins(50, 0); //50
        }
        else if(height <= 2000)
        {
            nSubsidy   = Utils.toNanoCoins(75, 0); //75
        }
        else if(height >= 2001)
        {
            nSubsidy  = Utils.toNanoCoins(100, 0); //100
        }
        nSubsidy = nSubsidy.shiftRight(height / subsidyDecreaseBlockCount);
        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 500000;     //main.cpp GetBlockValue(height, fee) checked

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // canecoin: starting difficulty is 1 / 2^12 checked

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature checked
    public static final String SATOSHI_KEY = "040184710fa689ad5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";
    public static final String TESTNET_SATOSHI_KEY = "04302390343f91cc401d56d68b123028bf52e5fca1939df127f63c6467cdf9c8e2c14b61104cf817d0b780da337893ecc4aaff1309e536162dabbdb45200ca2b0a";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.cancoin.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.cancoin.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.cancoin.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints checked
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put( 0, new Sha256Hash("863626dadaef221e2e2f30ff3dacae44cabdae9e0028058072181b3fb675d94a"));
    }

    //Unit Test Information
    public static final String UNITTEST_ADDRESS = "DPHYTSm3f96dHRY3VG1vZAFC1QrEPkEQnt";
    public static final String UNITTEST_ADDRESS_PRIVATE_KEY = "QU1rjHbrdJonVUgjT7Mncw7PEyPv3fMPvaGXp9EHDs1uzdJ98hUZ";

    public static boolean supportsIrcDiscovery() {
        return PROTOCOL_VERSION <= 70000;
    }

}
