SELECT ROUND(SUM(CAPACITY), 2) CAPACITY, VALUE
				   from (SELECT DECODE(C.VALUE,
				                       '1',
				                       '水电',
				                       '2',
				                       '光伏',
				                       '3',
				                       '风电',
				                       '5',
				                       '生物质能',
				                       '6',
				                       '潮汐',
				                       '7',
				                       '地热',
				                       '8',
				                       '抽水蓄能',
				                       '11',
				                       '生物质') VALUE,
				                C.NAME,
				                B.CAPACITY
				           from V_DW_UNITYTYPE C
				           LEFT JOIN (SELECT SUM(A.Generatorratedcap)  CAPACITY,
				                            A.GENERATORTYPE VALUE
				                       from ba_generator A
				                      WHERE A.MARKETID IN
				                            (SELECT MARKETID
				                               from DW_MARKET
				                              START WITH MARKETID = '91812'
				                             CONNECT BY PRIOR MARKETID = PARENTMARKETID)
				                        AND (A.Endeffectivedate >= to_date('2007/10/10','yyyy/MM/dd') or A.Endeffectivedate is null)
				                        AND A.GENERATORTYPE IN (1,2,3,4,5,6,7,8,11)
				                      GROUP BY A.GENERATORTYPE) B ON C.VALUE = B.VALUE ) rv where rv.capacity is not null
				  GROUP BY VALUE   ORDER BY VALUE DESC